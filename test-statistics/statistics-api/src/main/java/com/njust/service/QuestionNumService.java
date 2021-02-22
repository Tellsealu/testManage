package com.njust.service;

import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/8 20:14
 * @Version 1.0
 */
public interface QuestionNumService {
    Integer selectQuestionCount();

    Integer selectCouseCount();

    Integer selectQuestionTypeCount();

    Integer selectDeleted();

    List<Integer> selectMonthCount();
}
