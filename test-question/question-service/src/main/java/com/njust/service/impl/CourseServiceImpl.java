package com.njust.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.njust.domain.Course;
import com.njust.domain.CourseDto;
import com.njust.mapper.CourseMapper;
import com.njust.service.CourseService;
import com.njust.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/1 20:00
 * @Version 1.0
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public DataGridView listCoursePage(CourseDto courseDto) {
        Page<Course> page=new Page<>(courseDto.getPageNum(), courseDto.getPageSize());
        QueryWrapper<Course> qw=new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(courseDto.getCourseName()), Course.COL_COURSE_NAME, courseDto.getCourseName());
        qw.like(StringUtils.isNotBlank(courseDto.getCourseTeach()), Course.COL_COURSE_TEACH, courseDto.getCourseTeach());
        qw.eq(null!= courseDto.getStatus(),Course.COL_STATUS,courseDto.getStatus());
        qw.ge(null!= courseDto.getBeginTime(), Course.COL_CREATE_TIME, courseDto.getBeginTime());
        qw.le(null!= courseDto.getEndTime(), Course.COL_CREATE_TIME, courseDto.getEndTime());
        qw.orderByAsc(Course.COL_CREATE_TIME);
        this.courseMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public Course getOne(Long courseId) {
        return this.courseMapper.getById(courseId);
    }

    @Override
    public int addCourse(Course course) {
        return this.courseMapper.insert(course);
    }

    @Override
    public int updateCourse(CourseDto courseDto) {
        Course course=this.courseMapper.selectById(courseDto.getCourseId());
        if (null==course){
            return 0;
        }
        BeanUtils.copyProperties(courseDto,course);
        course.setUpdateBy(courseDto.getSimpleUser().getUserName());
        course.setUpdateTime(DateUtil.date());
        return this.courseMapper.updateById(course);
    }

    @Override
    public int deleteCourseByIds(Long[] courseIds) {
        List<Long> ids = Arrays.asList(courseIds);
        return this.courseMapper.deleteBatchIds(ids);
    }

    @Override
    public List<Course> selectAllCourse() {
        QueryWrapper<Course> qw=new QueryWrapper<>();
        qw.eq(Course.COL_STATUS,0);
        return this.courseMapper.selectList(qw);
    }
}
