package com.njust.service;

import com.njust.domain.QuestionType;
import com.njust.dto.QuestionTypeDto;
import com.njust.vo.DataGridView;

import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/1 19:17
 * @Version 1.0
 */
public interface QuestionTypeService {
    /**
     * 分页查询
     *
     * @param questionTypeDto
     * @return
     */
    DataGridView listQuestionTypePage(QuestionTypeDto questionTypeDto);

    /**
     * 根据ID查询
     *
     * @param questionTypeId
     * @return
     */
    QuestionType getOne(Integer questionTypeId);

    /**
     * 添加
     *
     * @param questionTypeDto
     * @return
     */
    int addQuestionType(QuestionTypeDto questionTypeDto);

    /**
     * 修改
     *
     * @param questionTypeDto
     * @return
     */
    int updateQuestionType(QuestionTypeDto questionTypeDto);

    /**
     * 根据ID删除
     *
     * @param QuestionTypeIds
     * @return
     */
    int deleteQuestionTypeByIds(Long[] QuestionTypeIds);

    List<QuestionType> selectAllQuestionType();

}
