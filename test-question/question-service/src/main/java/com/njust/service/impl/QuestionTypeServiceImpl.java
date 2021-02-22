package com.njust.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.njust.domain.QuestionType;
import com.njust.dto.QuestionTypeDto;
import com.njust.mapper.QuestionTypeMapper;
import com.njust.service.QuestionTypeService;
import com.njust.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/1 20:04
 * @Version 1.0
 */
@Service
public class QuestionTypeServiceImpl implements QuestionTypeService {

    @Autowired
    private QuestionTypeMapper questionTypeMapper;

    @Override
    public DataGridView listQuestionTypePage(QuestionTypeDto questionTypeDto) {
        Page<QuestionType> page=new Page<>(questionTypeDto.getPageNum(), questionTypeDto.getPageSize());
        QueryWrapper<QuestionType> qw=new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(questionTypeDto.getQuestionTypeName()), QuestionType.COL_QUESTION_TYPE_NAME,questionTypeDto.getQuestionTypeName());
        qw.like(StringUtils.isNotBlank(questionTypeDto.getQuestionTypeDescription()),QuestionType.COL_QUESTION_TYPE_DESCRIPTION,questionTypeDto.getQuestionTypeDescription() );
        qw.orderByAsc(QuestionType.COL_QUESTION_TYPE_ID);
        this.questionTypeMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public QuestionType getOne(Integer questionTypeId) {
        return this.questionTypeMapper.selectById(questionTypeId);
    }

    @Override
    public int addQuestionType(QuestionTypeDto questionTypeDto) {
        QuestionType questionType = new QuestionType();
        BeanUtils.copyProperties(questionTypeDto, questionType);
        return this.questionTypeMapper.insert(questionType);
    }

    @Override
    public int updateQuestionType(QuestionTypeDto questionTypeDto) {
        QuestionType questionType = this.questionTypeMapper.selectById(questionTypeDto.getQuestionTypeId());
        if (null == questionType) {
            return 0;
        }
        BeanUtils.copyProperties(questionTypeDto, questionType);

        return this.questionTypeMapper.updateById(questionType);
    }

    @Override
    public int deleteQuestionTypeByIds(Long[] QuestionTypeIds) {
        List<Long> list = Arrays.asList(QuestionTypeIds);
        return this.questionTypeMapper.deleteBatchIds(list);
    }

    @Override
    public List<QuestionType> selectAllQuestionType() {
        QueryWrapper<QuestionType> qw=new QueryWrapper<>();
        return this.questionTypeMapper.selectList(qw);
    }

}
