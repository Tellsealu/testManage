package com.njust.service;

import com.njust.domain.Course;
import com.njust.domain.CourseDto;
import com.njust.vo.DataGridView;

import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/1 19:17
 * @Version 1.0
 */
public interface CourseService {
    /**
     * 分页查询
     *
     * @param courseDto
     * @return
     */
    DataGridView listCoursePage(CourseDto courseDto);

    /**
     * 根据ID查询
     *
     * @param courseId
     * @return
     */
    Course getOne(Long courseId);

    /**
     * 添加
     *
     * @param course
     * @return
     */
    int addCourse(Course course);

    /**
     * 修改
     *
     * @param courseDto
     * @return
     */
    int updateCourse(CourseDto courseDto);

    /**
     * 根据ID删除
     *
     * @param courseIds
     * @return
     */
    int deleteCourseByIds(Long[] courseIds);

    /**
     * 查询所有
     */
    List<Course> selectAllCourse();

}
