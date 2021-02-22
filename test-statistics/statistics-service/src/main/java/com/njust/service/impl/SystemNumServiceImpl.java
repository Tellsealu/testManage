package com.njust.service.impl;

import com.njust.mapper.SystemNumMapper;
import com.njust.service.SystemNumService;
import com.njust.utils.DateTimeUtil;
import com.njust.utils.KeyValue;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author qufeng
 * @Date 2021/2/8 22:49
 * @Version 1.0
 */
@Service
public class SystemNumServiceImpl implements SystemNumService{

    @Autowired
    private SystemNumMapper systemNumMapper;

    @Override
    public Integer selectUserCount() {
        return systemNumMapper.selectUserCount();
    }

    @Override
    public Integer selectNoticeTypeCount() {
        return systemNumMapper.selectNoticeTypeCount();
    }

    @Override
    public Integer selectLoginCount() {
        return systemNumMapper.selectLoginCount();
    }

    @Override
    public Integer selectOperateCount() {
        return systemNumMapper.selectOperateCount();
    }

    @Override
    public List<Integer> selectUserAction() {
        Date startTime = DateTimeUtil.getMonthStartDay();
        Date endTime = DateTimeUtil.getMonthEndDay();
        List<KeyValue> mouthCount = systemNumMapper.selectUserAction(startTime, endTime);
        List<String> mothStartToNowFormat = DateTimeUtil.MothStartToNowFormat();
        return mothStartToNowFormat.stream().map(md -> {
            KeyValue keyValue = mouthCount.stream().filter(kv -> kv.getName().equals(md)).findAny().orElse(null);
            return null == keyValue ? 0 : keyValue.getValue();
        }).collect(Collectors.toList());
    }

    @Override
    public List<Integer> selectOperateAction() {
        Date startTime = DateTimeUtil.getMonthStartDay();
        Date endTime = DateTimeUtil.getMonthEndDay();
        List<KeyValue> mouthCount = systemNumMapper.selectOperateAction(startTime, endTime);
        List<String> mothStartToNowFormat = DateTimeUtil.MothStartToNowFormat();
        return mothStartToNowFormat.stream().map(md -> {
            KeyValue keyValue = mouthCount.stream().filter(kv -> kv.getName().equals(md)).findAny().orElse(null);
            return null == keyValue ? 0 : keyValue.getValue();
        }).collect(Collectors.toList());
    }
}
