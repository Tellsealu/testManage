package com.njust.utils.importExcel;

/**
 * @Author qufeng
 * @Date 2021/2/5 22:36
 * @Version 1.0
 */

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.njust.controller.question.QuestionController;
import com.njust.domain.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class UploadExcelListener extends AnalysisEventListener<Question> {

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
    public static final List<Question> list = new ArrayList<>();

    @Override
    public void invoke(Question excelEntity, AnalysisContext context) {
        list.add(excelEntity);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        for (Question q: list
             ) {
            System.out.println(q.toString());
        }
        LOGGER.info("所有数据解析完成！");

    }

}
