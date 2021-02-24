package com.njust.controller.question;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.njust.aspectj.annotation.Log;
import com.njust.aspectj.enums.BusinessType;
import com.njust.controller.BaseController;
import com.njust.domain.Course;
import com.njust.domain.CourseDto;
import com.njust.service.CourseService;
import com.njust.service.QuestionService;
import com.njust.service.ResourceService;
import com.njust.utils.ShiroSecurityUtils;
import com.njust.vo.AjaxResult;
import com.njust.vo.DataGridView;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author qufeng
 * @Date 2021/2/1 20:12
 * @Version 1.0
 */

@RestController
@RequestMapping("question/course")
public class CourseController extends BaseController {

    @Reference
    private CourseService courseService;

    @Reference
    private QuestionService questionService;

    @Reference
    private ResourceService resourceService;

    /**
     * 分页查询
     */
    @GetMapping("listCourseForPage")
    public AjaxResult listCourseForPage(CourseDto courseDto){
        DataGridView gridView = this.courseService.listCoursePage(courseDto);
        return AjaxResult.success("查询成功",gridView.getData(),gridView.getTotal());
    }

    /**
     * 添加
     */
    @PostMapping("addCourse")
    @Log(title = "添加课程",businessType = BusinessType.INSERT)
    public AjaxResult addCourse(@Validated CourseDto courseDto) {
        courseDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        Course course=new Course();
        BeanUtil.copyProperties(courseDto,course);
        course.setCreateBy(courseDto.getSimpleUser().getUserName());
        course.setCreateTime(DateUtil.date());
        return AjaxResult.toAjax(this.courseService.addCourse(course));
    }

    /**
     * 修改课程
     */
    @PutMapping("updateCourse")
    @Log(title = "修改课程",businessType = BusinessType.UPDATE)
    public AjaxResult updateCourse(@Validated CourseDto courseDto) {
        courseDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.courseService.updateCourse(courseDto));
    }


    /**
     * 根据ID查询一个用户信息
     */
    @GetMapping("getCourseById/{courseId}")
    public AjaxResult getCourseById(@PathVariable @Validated @NotNull(message = "课程ID不能为空") Long courseId) {
        return AjaxResult.success(this.courseService.getOne(courseId));
    }

    /**
     * 删除
     */
    @DeleteMapping("deleteCourseByIds/{courseIds}")
    @Log(title = "删除课程",businessType = BusinessType.DELETE)
    public AjaxResult deleteCourseByIds(@PathVariable @Validated @NotEmpty(message = "要删除的ID不能为空") Long[] courseIds) {
        Integer questionCount;
        Integer resourceCount;
        Boolean count = false;
        for (int i = 0; i < courseIds.length; i++) {
            questionCount = this.questionService.seleceQuestionCount(courseIds[i]);
            resourceCount = this.resourceService.selectResourceCount(courseIds[i]);
            if (questionCount != 0 || resourceCount != 0) {
                count = true;
                break;
            }
        }
        if (count) {
            return new AjaxResult(201, "课程已绑定试题或者资源，无法删除");
        } else {
            return AjaxResult.toAjax(this.courseService.deleteCourseByIds(courseIds));
        }
    }
    /**
     * 查询所有可用课程
     */
    @GetMapping("selectAllCourse")
    public AjaxResult selectAllCourse(){
        return AjaxResult.success(this.courseService.selectAllCourse());

    }
}
