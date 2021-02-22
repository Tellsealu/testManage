package com.njust.controller.question;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.fastjson.JSONObject;
import com.njust.aspectj.annotation.Log;
import com.njust.aspectj.enums.BusinessType;
import com.njust.controller.BaseController;
import com.njust.domain.Question;
import com.njust.dto.ExportDto;
import com.njust.dto.QuestionDto;
import com.njust.entity.QuestionEntity;
import com.njust.service.ExportService;
import com.njust.service.QuestionService;
import com.njust.service.ResourceService;
import com.njust.utils.*;
import com.njust.utils.importExcel.UploadExcelListener;
import com.njust.vo.AjaxResult;
import com.njust.vo.DataGridView;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/2 19:39
 * @Version 1.0
 */
@RestController
@RequestMapping("question/question")
public class QuestionController extends BaseController {

    @Reference
    private QuestionService questionService;

    @Reference
    private ResourceService resourceService;

    @Autowired
    private AsyncTaskEngine asyncTaskEngine;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ExportService exportService;
    /**
     * 分页查询
     */
    @GetMapping("listQuestionForPage")
    public AjaxResult listQuestionForPage(QuestionDto questionDto){
        DataGridView gridView = this.questionService.listQuestionPage(questionDto);
        return AjaxResult.success("查询成功",gridView.getData(),gridView.getTotal());
    }

    /**
     * 添加
     */
    @PostMapping("addQuestion")
    @Log(title = "添加试题",businessType = BusinessType.INSERT)
    public AjaxResult addQuestionType(@Validated QuestionDto questionDto) {
        questionDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.questionService.addQuestion(questionDto));
    }

    /**
     * 修改
     */
    @PutMapping("updateQuestion")
    @Log(title = "修改试题",businessType = BusinessType.UPDATE)
    public AjaxResult updateQuestionType(@Validated QuestionDto questionDto) {
        questionDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.questionService.updateQuestion(questionDto));
    }


    /**
     * 根据ID查询一个
     */
    @GetMapping("getQuestionById/{questionId}")
    public AjaxResult getQuestionById(@PathVariable @Validated @NotNull(message = "试题ID不能为空") String questionId) {
        return AjaxResult.success(this.questionService.getOne(questionId));
    }

    /**
     * 删除
     */
    @DeleteMapping("deleteQuestionByIds/{questionIds}")
    @Log(title = "删除试题",businessType = BusinessType.DELETE)
    public AjaxResult deleteQuestionTypeByIds(@PathVariable @Validated @NotEmpty(message = "要删除的ID不能为空") String[] questionIds) {
        return AjaxResult.toAjax(this.questionService.deleteQuestionByIds(questionIds));
    }

    /*
    *导入Excel
    * */
    @PostMapping("upload")
    public AjaxResult upload(@RequestParam("file") MultipartFile file)throws IOException {
        boolean error=false;
        //获取文件名
        String uploadFileName = file.getOriginalFilename();
        //首先判断是不是空的文件
        if (!file.isEmpty()) {
            //对文文件的全名进行截取然后在后缀名进行删选。
            int begin = file.getOriginalFilename().indexOf(".");
            int last = file.getOriginalFilename().length();
            //获得文件后缀名
            String a = file.getOriginalFilename().substring(begin, last);
            if (a.endsWith(".xlsx")||a.endsWith(".xls")) {
                ExcelReader excelReader = null;
                String resutl = null;
                InputStream in = null;
                try {
                    in = file.getInputStream();
                    try{
                        EasyExcel.read(in, Question.class, new UploadExcelListener()).doReadAll();
                    }catch (Exception e){
                        e.printStackTrace();
                        error=true;
                    }
                    List<Question> list = UploadExcelListener.list;
                    int time=0;
                    for (Question q: list) {
                        this.questionService.addQuestionExcel(q);
                        time++;
                        System.out.println(q.toString()+"第"+time+"次循环插入数据库");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    in.close();
                    // 这里一定别忘记关闭，读的时候会创建临时文件，到时磁盘会崩
                    if (excelReader != null) {
                        excelReader.finish();
                    }
                }
            } else {
                return AjaxResult.success("文件格式错误");

            }
        }else {
            return AjaxResult.success("文件不能为空");
        }
        if (!error){
            return AjaxResult.success("导入成功") ;
        }else {
            return AjaxResult.success("导入异常") ;
        }

    }

    /*
    * 同步导出全部Excel
    *
    * */
    @RequestMapping(value = "exportAllExcel",method = RequestMethod.GET)
    public void exportAllExcel(HttpServletResponse response)throws Exception{
        //查询所有数据并导出
        List<QuestionEntity>list=new ArrayList<>();
        list=questionService.selectForExcel();
        //通知浏览器以附件的形式下载处理，设置返回头要注意文件名有中文
        String fileName = URLEncoder.encode("demo", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");

        EasyExcel.write(response.getOutputStream(), Question.class).registerWriteHandler(new Custemhandler()).sheet("0").doWrite(list);
    }
    /*
     * 异步导出全部Excel
     *
     * */
    @RequestMapping(value = "exportAllExcelAsync",method = RequestMethod.GET)
    public AjaxResult exportAllExcelAsync(ExportDto exportDto)throws Exception{
//        asyncTaskEngine.run();
        String exportId= IdGeneratorSnowflake.generatorIdWithProfix("excel");
        com.alibaba.fastjson.JSONObject jsonObject =new JSONObject();
        jsonObject.put("taskNo", AsyncTask.EXPORT_ALL_QUESTION.getNo());
        jsonObject.put("exportId",exportId);
        /*获取用户名*/
        String username=ShiroSecurityUtils.getCurrentSimpleUser().getUserName();
        jsonObject.put("username",username);
        asyncTaskEngine.pushEvent(jsonObject);
        asyncTaskEngine.start();

        return new AjaxResult(200,"导出任务已创建");
    }
    /*
    * 下载试题模板
    * */
    @GetMapping(value = "/downloadExcelModel")
    public ResponseEntity<byte[]> download(HttpServletRequest response) throws Exception{

        String fileName="导入试题模板";
        String modelUrl=this.resourceService.selectByQuestionExcelName(fileName);

        HttpHeaders headers = new HttpHeaders();
        //处理IE
        String userAgent = response.getHeader("user-agent").toLowerCase();

        if (userAgent.contains("msie") || userAgent.contains("like gecko")  ||
                userAgent.contains("Trident")) {
            // win10 ie edge 浏览器 和其他系统的ie
            fileName = URLEncoder.encode(fileName, "UTF-8");
            //解决下载时，空格变加号
            fileName = org.apache.commons.lang3.StringUtils.replace(fileName, "+", "%20");
        } else {
            // fe
            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");

            //解决下载时，空格变加号
            fileName = org.apache.commons.lang3.StringUtils.replace(fileName, "+", "%20");
        }

        //通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment",fileName); //解决原始文件名中有中文出现乱码);
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(IOUtils.toByteArray(getFileInputStream(modelUrl)),
                headers, HttpStatus.CREATED);

    }
    /*
    * 导出选中
    *
    * */
    @GetMapping("exportByIds/{questionIds}")
    @Log(title = "导出选中试题",businessType = BusinessType.DELETE)
    public void exportByIds(@PathVariable @Validated @NotEmpty(message = "要导出的ID不能为空") String[] questionIds,
                                  HttpServletResponse response)throws Exception {
        List<QuestionEntity> list=new ArrayList<>();
        list=this.questionService.exportByIds(questionIds);
        for (QuestionEntity q: list
             ) {
            System.out.println(q.toString());
        }
        //通知浏览器以附件的形式下载处理，设置返回头要注意文件名有中文
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYYMMDDmmss");//日期指定格式输出
        Date date=new Date();
        System.out.println(simpleDateFormat.format(date));//指定格式输出
        String fileName = URLEncoder.encode(date+"导出试题", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        EasyExcel.write(response.getOutputStream(), QuestionEntity.class)
                .registerWriteHandler(new Custemhandler())
                .sheet("0")
                .doWrite(list);
    }

    /*
    * 查询所有导出文件
    * */
    @GetMapping("selectAllExport")
    public AjaxResult selectAllExport(ExportDto exportDto){
        DataGridView gridView = this.exportService.listQuestionPage(exportDto);
        return AjaxResult.success("查询成功",gridView.getData(),gridView.getTotal());
    }




    //输入流私有方法
    public InputStream getFileInputStream(String urlString) {
        InputStream is = null;
        try {
            // 构造URL
            URL url = new URL(urlString);
            // 打开连接
            URLConnection con = url.openConnection();
            // 输入流
            is = con.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;
    }


}
