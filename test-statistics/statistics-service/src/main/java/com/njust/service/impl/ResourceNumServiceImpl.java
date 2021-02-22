package com.njust.service.impl;

import com.njust.mapper.ResourceNumMapper;
import com.njust.service.ResourceNumService;
import com.njust.utils.DateTimeUtil;
import com.njust.utils.KeyValue;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author qufeng
 * @Date 2021/2/8 23:30
 * @Version 1.0
 */
@Service
public class ResourceNumServiceImpl implements ResourceNumService {
    @Autowired
    private ResourceNumMapper resourceNumMapper;

    @Override
    public Integer selectResourceCount() {
        return resourceNumMapper.selectResourceCount();
    }

    @Override
    public Integer selectResourceTypeCount() {
        return resourceNumMapper.selectResourceTypeCount();
    }

    @Override
    public Integer selectDeleted() {
        return resourceNumMapper.selectDeleted();
    }

    @Override
    public List<Integer> selectMonthCount() {
        Date startTime = DateTimeUtil.getMonthStartDay();
        Date endTime = DateTimeUtil.getMonthEndDay();
        List<KeyValue> mouthCount = resourceNumMapper.selectMonthCount(startTime, endTime);
        List<String> mothStartToNowFormat = DateTimeUtil.MothStartToNowFormat();
        return mothStartToNowFormat.stream().map(md -> {
            KeyValue keyValue = mouthCount.stream().filter(kv -> kv.getName().equals(md)).findAny().orElse(null);
            return null == keyValue ? 0 : keyValue.getValue();
        }).collect(Collectors.toList());
    }
}
