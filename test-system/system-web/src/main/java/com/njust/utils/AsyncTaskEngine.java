package com.njust.utils;

/**
 * @Author qufeng
 * @Date 2021/2/22 16:44
 * @Version 1.0
 */

import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.njust.config.upload.UploadService;
import com.njust.domain.Export;
import com.njust.domain.Question;
import com.njust.entity.QuestionEntity;
import com.njust.service.ExportService;
import com.njust.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 异步执行任务队列
 * @author ：
 * @date ：
 */
@Component("asyncTaskEngine")
public class AsyncTaskEngine extends Thread{

    private static Logger logger = LoggerFactory.getLogger(AsyncTaskEngine.class);
    private static boolean terminated = false;
    private static String ASYNC_TASK_QUEUE_KEY = "trm.async.task.queue";


    private static StringRedisTemplate stringRedisTemplate;
//    private static NumberRemarkMapper numberRemarkMapper;
////    private static NumberCertificateService numberCertificateService;
////    private static EnterpriseNumberMapper enterpriseNumberMapper;
    private static ExportService exportService;
    private static QuestionService questionService;
    private static UploadService uploadService;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate){
        AsyncTaskEngine.stringRedisTemplate=stringRedisTemplate;
    }
    @Autowired
    public void setExportService(ExportService exportService){
        AsyncTaskEngine.exportService=exportService;
    }
    @Autowired
    public void setUploadService(UploadService uploadService){
        AsyncTaskEngine.uploadService=uploadService;
    }
    public static void init(ApplicationContext applicationContext) {
        stringRedisTemplate = (StringRedisTemplate) applicationContext.getBean("stringRedisTemplate");
//        numberRemarkMapper = (NumberRemarkMapper) applicationContext.getBean("numberRemarkMapper");
//        numberCertificateService = (NumberCertificateService) applicationContext.getBean("numberCertificateService");
//        enterpriseNumberMapper = (EnterpriseNumberMapper) applicationContext.getBean("enterpriseNumberMapper");
        uploadService = (UploadService) applicationContext.getBean("uploadService");
        exportService = (ExportService) applicationContext.getBean("exportService");
        questionService=(QuestionService) applicationContext.getBean("questionService");
        AsyncTaskEngine asyncTaskEngine = new AsyncTaskEngine();
        asyncTaskEngine.setName("asyncTaskEngine-" + 0);
        asyncTaskEngine.start();
    }

    public AsyncTaskEngine(){
    }


    public void pushEvent(JSONObject jsonObject){
        try {
            if (jsonObject != null && Objects.nonNull(jsonObject)){
                //异步引擎
                stringRedisTemplate.opsForList().leftPush(ASYNC_TASK_QUEUE_KEY, jsonObject.toJSONString());
            }
        }catch (Exception e){
            logger.error("AsyncTaskEngine pushEvent error:{}", e);
        }
    }

    @Override
    public void run() {
        logger.info("AsyncTaskEngine.start...");
        String taskMsg = null;
        while (!terminated) {
            try {
                taskMsg =  stringRedisTemplate.opsForList().rightPop(ASYNC_TASK_QUEUE_KEY, 60, TimeUnit.SECONDS);
//                taskMsg=exportService.selectForJson();
                if (StringUtils.isEmpty(taskMsg)) {
                    continue;
                }
                logger.info("AsyncTaskEngine.taskMsg:{}", taskMsg);
                JSONObject jsonObject = JSONObject.parseObject(taskMsg.toString());
                String taskNo = jsonObject.getString("taskNo");
                AsyncTask asyncTask = AsyncTask.getAsyncTask(taskNo);
                switch (asyncTask) {
//                    case UPDATE_NUMBER_REMARK:
//                        List<String> trunks = (List<String>) jsonObject.get("trunks");
//                        List<NumberRemark> numberRemarks = numberRemarkMapper.selectByTrunkNumber(trunks);
//                        numberCertificateService.numberCertificateInfoAndRemark(numberRemarks, false);
//                        break;
//                    case UPDATE_ENTERPRISE_NAME_By_ENTERPRISE_Id:
//                        List<EnterpriseNumber> enterpriseNumberList = (List<EnterpriseNumber>) jsonObject.get("enterpriseNumberList");
//                        enterpriseNumberMapper.updateEnterpriseNameByEnterpriseId(enterpriseNumberList);
//                        break;
                    //导出全部问题
                    case EXPORT_ALL_QUESTION:
                        String exportId = jsonObject.getString("exportId");
                        String username = jsonObject.getString("username");
                        List<QuestionEntity>list=exportService.exportAllQuestion(exportId, username);
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        EasyExcel.write(byteArrayOutputStream,
                                Question.class).
                                registerWriteHandler(new Custemhandler()).
                                sheet("0").
                                doWrite(list);
                        MultipartFile file = new MockMultipartFile("file",
                                "xxx.xlsx",
                                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                                byteArrayOutputStream.toByteArray());
                        String downloadUrl=uploadService.uploadImage(file);
                        logger.info("下载url",downloadUrl);
                        Export export=new Export();
                        export.setExpoerId(exportId);
                        export.setDownloadUrl(downloadUrl);
                        export.setUsername(username);
                        export.setExportTime(DateUtil.date());
                        export.setExportType(0);
                        exportService.insertExport(export);
                        break;
//                    case EXPORT_HOTLINE_NUMBER:
//                        //导出热线号码
//                        NumberQueryParam numberQueryParam = jsonObject.getObject("number", NumberQueryParam.class);
//                        numberService.upload(numberQueryParam, jsonObject.getString("exportId"), jsonObject.getString("username"));
//                        break;
                    default:
                        logger.info("asyncTask {}", asyncTask);
                }
            } catch (Exception e) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e1) {
                    logger.error(e1.toString(), e1);
                }
                logger.error("AsyncTaskEngine run error:{}", e);
            }
        }
        logger.info("AsyncTaskEngine.end...");
    }
}