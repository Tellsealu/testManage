package com.njust.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.njust.domain.Export;
import com.njust.entity.QuestionEntity;

import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/22 18:12
 * @Version 1.0
 */
public interface ExportMapper extends BaseMapper<Export> {
    List<QuestionEntity> selectAllQuestion();

    String selectForJson();
}
