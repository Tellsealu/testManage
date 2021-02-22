package com.njust.service;

import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/8 22:28
 * @Version 1.0
 */
public interface ResourceNumService {
    Integer selectResourceCount();

    Integer selectResourceTypeCount();

    Integer selectDeleted();

    List<Integer> selectMonthCount();
}
