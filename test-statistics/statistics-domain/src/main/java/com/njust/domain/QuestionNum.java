package com.njust.domain;

import lombok.Data;

import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/8 19:13
 * @Version 1.0
 */
@Data
public class QuestionNum {
    private Integer courseCount;
    private Integer questionTypeCount;
    private Integer questionCount;
    private Integer questionDeleted;
    private List<Integer> mothDayDoExamQuestionValue;
    private List<String> mothDayText;
}
