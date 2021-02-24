package com.njust.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.njust.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author qufeng
 * @Date 2021/2/1 18:08
 * @Version 1.0
 */
@ApiModel(value="com-njust-domain-Course")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "course")
public class CourseDto extends BaseDto {


    /**
     * 课程ID
     */
    @TableId(value = "course_id", type = IdType.INPUT)
    @ApiModelProperty(value="课程ID 全局ID雪花算法")
    private Integer courseId;

    /**
     * 课程名称
     */
    @TableId(value = "course_name")
    @ApiModelProperty(value="课程名称")
    private String courseName;

    /**
     * 授课年度
     */
    @TableId(value = "course_year")
    @ApiModelProperty(value="课程年度")
    private String courseYear;


    /**
     * 课程名称
     */
    @TableId(value = "course_name")
    @ApiModelProperty(value="授课老师")
    private String courseTeach;

    /*
     * 状态*/
    @TableField(value = "status")
    private Integer status;
}
