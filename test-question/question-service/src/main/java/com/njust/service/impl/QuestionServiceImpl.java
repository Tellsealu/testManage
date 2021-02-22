package com.njust.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.njust.domain.Question;
import com.njust.dto.QuestionDto;
import com.njust.entity.QuestionEntity;
import com.njust.mapper.QuestionMapper;
import com.njust.service.QuestionService;
import com.njust.utils.Custemhandler;
import com.njust.utils.IdGeneratorSnowflake;
import com.njust.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/1 20:03
 * @Version 1.0
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public DataGridView listQuestionPage(QuestionDto questionDto) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Question> page=new Page<>(questionDto.getPageNum(), questionDto.getPageSize());
        QueryWrapper<Question> qw=new QueryWrapper<>();
        questionDto.setDeleted(0);
        qw.like(StringUtils.isNotBlank(questionDto.getQuestionName()), Question.COL_QUESTION_NAME,questionDto.getQuestionName());
        qw.like(StringUtils.isNotBlank(questionDto.getQuestionAnswer()), Question.COL_QUESTION_ANSWER,questionDto.getQuestionAnswer());
        qw.like(StringUtils.isNotBlank(questionDto.getQuestionDescription()),Question.COL_QUESTION_DESCRIPTION,questionDto.getQuestionDescription());
        qw.eq(questionDto.getQuestionCourseId()!=null,Question.COL_QUESTION_COURSE_ID,questionDto.getQuestionCourseId());
        qw.eq(questionDto.getQuestionTypeId()!=null,Question.COL_QUESTION_TYPE_ID,questionDto.getQuestionTypeId());
        qw.ge(null!= questionDto.getBeginTime(), Question.COL_CREATE_TIME, questionDto.getBeginTime());
        qw.eq(Question.COL_QUESTION_DELETED,questionDto.getDeleted());
        qw.le(null!= questionDto.getEndTime(), Question.COL_CREATE_TIME, questionDto.getEndTime());
        qw.orderByAsc(Question.COL_CREATE_TIME);
        this.questionMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public Question getOne(String questionId) {
        return this.questionMapper.selectById(questionId);
    }

    @Override
    public int addQuestion(QuestionDto questionDto) {
        Question question = new Question();
        BeanUtils.copyProperties(questionDto, question);
        question.setQuestionId(IdGeneratorSnowflake.snowflakeId().toString());
        question.setDeleted(0);
        question.setCreateTime(DateUtil.date());
        question.setCreateBy(questionDto.getSimpleUser().getUserName());
        return this.questionMapper.insert(question);
    }

    @Override
    public int addQuestionExcel(Question question1){
        question1.setDeleted(0);
        question1.setQuestionId(IdGeneratorSnowflake.snowflakeId().toString());
        question1.setCreateTime(DateUtil.date());
        question1.setCreateBy("导入");
        System.out.println(question1.toString());
        return this.questionMapper.insert(question1);
    }

    @Override
    public int updateQuestion(QuestionDto questionDto) {
        Question question = this.questionMapper.selectById(questionDto.getQuestionId());
        if (null == question) {
            return 0;
        }
        BeanUtils.copyProperties(questionDto, question);
        question.setUpdateBy(questionDto.getSimpleUser().getUserName());
        question.setUpdateTime(DateUtil.date());
        return this.questionMapper.updateById(question);
    }

    @Override
    public int deleteQuestionByIds(String[] QuestionIds) {
        if (QuestionIds.length>1) {
            List<String> list = Arrays.asList(QuestionIds);
            for (String id : list) {
                this.questionMapper.deletedById(id);
            }
        }else {
            String id=QuestionIds[0];
            this.questionMapper.deletedById(id);
        }
        return 1;
    }

    @Override
    public List<QuestionEntity> selectForExcel() {
        return this.questionMapper.selectForExcel();
    }

    @Override
    public List<QuestionEntity> exportByIds(String[] questionIds) {
        List<String> list = Arrays.asList(questionIds);
        return this.questionMapper.exportByIds(list);
    }

    @Override
    public Integer selectQuestionCount() {
        return 0;
    }

    @Override
    public Integer selectDeleted() {
        return null;
    }

    @Override
    public void exportAll(String exportId, String username) {
        List<QuestionEntity> list = this.questionMapper.selectForExcel();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        EasyExcel.write(byteArrayOutputStream,
                Question.class).registerWriteHandler(new Custemhandler()).sheet("0").doWrite(list);

    }

}

