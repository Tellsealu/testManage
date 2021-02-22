package com.njust.service;

import com.njust.domain.Question;
import com.njust.dto.QuestionDto;
import com.njust.entity.QuestionEntity;
import com.njust.vo.DataGridView;

import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/1 19:17
 * @Version 1.0
 */
public interface QuestionService {
    /**
     * 分页查询
     *
     * @param questionDto
     * @return
     */
    DataGridView listQuestionPage(QuestionDto questionDto);

    /**
     * 根据ID查询
     *
     * @param questionId
     * @return
     */
    Question getOne(String questionId);

    /**
     * 添加
     *
     * @param questionDto
     * @return
     */
    int addQuestion(QuestionDto questionDto);
    int addQuestionExcel(Question question);

    /**
     * 修改
     *
     * @param questionDto
     * @return
     */
    int updateQuestion(QuestionDto questionDto);

    /**
     * 根据ID删除
     *
     * @param questionIds
     * @return
     */
    int deleteQuestionByIds(String[] questionIds);

    List<QuestionEntity> selectForExcel();

    List<QuestionEntity> exportByIds(String[] questionIds);

    Integer selectQuestionCount();

    Integer selectDeleted();

    void exportAll(String exportId, String username);
}
