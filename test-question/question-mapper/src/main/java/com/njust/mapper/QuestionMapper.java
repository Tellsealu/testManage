package com.njust.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.njust.domain.Question;
import com.njust.entity.QuestionEntity;

import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/1 19:34
 * @Version 1.0
 */
public interface QuestionMapper extends BaseMapper<Question> {
//    List<Question> getByPage();

    void deletedById(String id);

    List<QuestionEntity> selectForExcel();

    List<QuestionEntity> exportByIds(List<String> list);
}
