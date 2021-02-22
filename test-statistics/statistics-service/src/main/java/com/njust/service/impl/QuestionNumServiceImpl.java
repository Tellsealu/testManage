package com.njust.service.impl;

import com.njust.mapper.QuestionNumMapper;
import com.njust.service.QuestionNumService;
import com.njust.utils.DateTimeUtil;
import com.njust.utils.KeyValue;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author qufeng
 * @Date 2021/2/8 20:15
 * @Version 1.0
 */
@Service
public class QuestionNumServiceImpl implements QuestionNumService {

    @Autowired
    private QuestionNumMapper questionNumMapper;
    @Override
    public Integer selectQuestionCount() {
        return this.questionNumMapper.selectQuestionCount();
    }

    @Override
    public Integer selectCouseCount() {
        return this.questionNumMapper.selectCouseCount();
    }

    @Override
    public Integer selectQuestionTypeCount() {
        return questionNumMapper.selectQuestionTypeCount();
    }

    @Override
    public Integer selectDeleted() {
        return questionNumMapper.selectDeleted();
    }

    @Override
    public List<Integer> selectMonthCount() {
        Date startTime = DateTimeUtil.getMonthStartDay();
        Date endTime = DateTimeUtil.getMonthEndDay();
        List<String> mothStartToNowFormat = DateTimeUtil.MothStartToNowFormat();
        List<KeyValue> mouthCount = questionNumMapper.selectCountByDate(startTime, endTime);
        return mothStartToNowFormat.stream().map(md -> {
            KeyValue keyValue = mouthCount.stream().filter(kv -> kv.getName().equals(md)).findAny().orElse(null);
            return null == keyValue ? 0 : keyValue.getValue();
        }).collect(Collectors.toList());
    }

}
