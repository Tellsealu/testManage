package com.njust.controller.statistics;

import com.njust.domain.QuestionNum;
import com.njust.service.QuestionNumService;
import com.njust.utils.DateTimeUtil;
import com.njust.vo.AjaxResult;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/8 14:54
 * @Version 1.0
 */
@RestController
@RequestMapping("statistics/question")
public class QuestionStatisticsController {


    @Reference
    private QuestionNumService questionNumService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public AjaxResult selectQuestionCount() {

        QuestionNum questionNum=new QuestionNum();

        //课程总数
        Integer courseCount=this.questionNumService.selectCouseCount();

        //试题类型总数
        Integer questionTypeCount=this.questionNumService.selectQuestionTypeCount();

        //试题总数
        Integer questionCount=this.questionNumService.selectQuestionCount();


        //已删除试题数
        Integer questionDeleted=this.questionNumService.selectDeleted();

        questionNum.setCourseCount(courseCount);
        questionNum.setQuestionTypeCount(questionTypeCount);
        questionNum.setQuestionCount(questionCount);
        questionNum.setQuestionDeleted(questionDeleted);


//        List<Integer> mothDayUserActionValue = new ArrayList<>();
        List<Integer> mothDayDoExamQuestionValue =new ArrayList<>();
        mothDayDoExamQuestionValue=this.questionNumService.selectMonthCount();
        questionNum.setMothDayDoExamQuestionValue(mothDayDoExamQuestionValue);

        questionNum.setMothDayText(DateTimeUtil.MothDay());
        return AjaxResult.success(questionNum);
    }
}