package com.njust.mapper;

import com.njust.utils.KeyValue;

import java.util.Date;
import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/8 22:48
 * @Version 1.0
 */
public interface SystemNumMapper {
    Integer selectUserCount();

    Integer selectNoticeTypeCount();

    Integer selectLoginCount();

    Integer selectOperateCount();

    List<KeyValue> selectUserAction(Date startTime, Date endTime);

    List<KeyValue> selectOperateAction(Date startTime, Date endTime);
}
