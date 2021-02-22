package com.njust.service;

import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/8 22:48
 * @Version 1.0
 */
public interface SystemNumService {
    Integer selectUserCount();

    Integer selectNoticeTypeCount();

    Integer selectLoginCount();

    Integer selectOperateCount();

    List<Integer> selectUserAction();

    List<Integer> selectOperateAction();
}
