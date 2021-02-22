package com.njust.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

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
public class Course implements Serializable {


    /**
     * 课程ID
     */
    @TableId(value = "course_id", type = IdType.INPUT)
    @ApiModelProperty(value="课程ID ")
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
    @TableId(value = "course_teach")
    @ApiModelProperty(value="授课老师")
    private String courseTeach;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    @ApiModelProperty(value="创建者")
    private String createBy;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    @ApiModelProperty(value="更新者")
    private String updateBy;

    public static final String COL_COURSE_ID = "course_id";

    public static final String COL_COURSE_NAME = "course_name";

    public static final String COL_COURSE_YEAR = "course_year";

    public static final String COL_COURSE_TEACH = "course_teach";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_UPDATE_BY = "update_by";

}
