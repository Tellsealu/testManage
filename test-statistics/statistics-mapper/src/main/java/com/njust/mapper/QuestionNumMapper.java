package com.njust.mapper;

import com.njust.utils.KeyValue;

import java.util.Date;
import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/8 20:16
 * @Version 1.0
 */
public interface QuestionNumMapper {
    Integer selectQuestionCount();

    Integer selectCouseCount();

    Integer selectQuestionTypeCount();

    Integer selectDeleted();

    List<KeyValue> selectCountByDate(Date startTime, Date endTime);
}
