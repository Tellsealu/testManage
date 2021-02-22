package com.njust.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.njust.domain.Resource;

/**
 * @Author qufeng
 * @Date 2021/2/1 19:34
 * @Version 1.0
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    void deletedById(String id);

    String selectByName(String fileName);

    String selectByUserExcelName(String fileName);
}
