package com.njust.utils.importExcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.njust.controller.question.QuestionController;
import com.njust.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * @Author qufeng
 * @Date 2021/2/17 20:39
 * @Version 1.0
 */
public class UserExcelListener extends AnalysisEventListener<UserEntity> {

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
    public static final List<UserEntity> list = new ArrayList<>();

    @Override
    public void invoke(UserEntity excelEntity, AnalysisContext context) {
        list.add(excelEntity);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        int a=list.size();
        LOGGER.info("导入用户所有数据解析完成！"+a+"条数据");

    }

}
