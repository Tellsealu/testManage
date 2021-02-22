package com.njust.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.njust.domain.Course;

import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/1 19:31
 * @Version 1.0
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<Course> getByMybatis();

    /**
     * 根据id查询
     * @param courseId
     * @return
     */
    Course getById(Long courseId);
}
