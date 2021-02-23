package com.njust.service;

import com.njust.domain.Export;
import com.njust.dto.ExportDto;
import com.njust.entity.QuestionEntity;
import com.njust.vo.DataGridView;

import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/22 18:07
 * @Version 1.0
 */
public interface ExportService {
    /**
     * 添加
     *
     */
    int insertExport(Export export);

    /**
     * 分页查询
     *
     * @return
     */
    DataGridView listForPage(ExportDto exportDto);

    /**
     * 删除
     *
     * @return
     */
    int deleteExportByIds(String[] exportIds);

    /**
     * 清空
     *
     * @return
     */
    int clearExport();

    /*
    * 导出全部试题
    * */
    List<QuestionEntity> exportAllQuestion(String exportId, String username);

    String selectForJson();

    DataGridView listQuestionPage(ExportDto exportDto);

    List<Export> deleteAllExcel();
}
