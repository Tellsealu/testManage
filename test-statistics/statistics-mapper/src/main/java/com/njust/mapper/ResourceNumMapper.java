package com.njust.mapper;

import com.njust.utils.KeyValue;

import java.util.Date;
import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/8 22:28
 * @Version 1.0
 */
public interface ResourceNumMapper {
    Integer selectResourceCount();

    Integer selectResourceTypeCount();

    Integer selectDeleted();

    List<KeyValue> selectMonthCount(Date startTime, Date endTime);
}
