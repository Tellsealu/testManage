package com.njust.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author qufeng
 * @Date 2021/2/1 18:55
 * @Version 1.0
 */
@ApiModel(value="com-njust-domain-Question")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "question")
public class QuestionDto extends BaseDto{

    /**
     * 试题ID
     */
    @TableId(value = "question_id", type = IdType.INPUT)
    @ApiModelProperty(value="试题ID 全局ID雪花算法")
    private String questionId;

    /**
     * 试题名称
     */
    @ExcelProperty(value = "试题名称",index = 0)
    @TableId(value = "question_name")
    @ApiModelProperty(value="试题名称")
    private String questionName;

    /**
     * 课程分数
     */
    @ExcelProperty(value = "试题分数",index = 1)
    @TableId(value = "question_score")
    @ApiModelProperty(value="试题分数")
    private Integer questionScore;

    /**
     * 试题类型
     */
    @ExcelProperty(value = "试题类型",index = 2)
    @TableId(value = "question_type_id")
    @ApiModelProperty(value="试题类型")
    private Integer questionTypeId;

    /**
     * 试题答案
     */
    @ExcelProperty(value = "试题答案",index = 3)
    @TableId(value = "question_answer")
    @ApiModelProperty(value="试题答案")
    private String questionAnswer;


    /**
     * 课程ID
     */
    @ExcelProperty(value = "所属课程",index = 4)
    @TableId(value = "question_course_id")
    @ApiModelProperty(value="所属课程")
    private Integer questionCourseId;

    /**
     * 额外描述
     */
    @ExcelProperty(value = "试题描述",index = 5)
    @TableId(value = "question_description")
    @ApiModelProperty(value="描述")
    private String questionDescription;

    /*
     * 试题图片地址
     * */
    @ColumnWidth(40)
    @ExcelProperty(value ="图片URL",index = 6)
    @TableId(value = "question_img")
    private String questionImg;

    /**
     * 难度级别
     */
    @ExcelProperty(value = "难度级别",index = 7)
    @TableId(value = "question_level")
    private String questionLevel;

    /**
     * 创建时间
     */
    @ExcelIgnore
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ExcelIgnore
    @TableField(value = "update_time")
    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    /**
     * 创建者
     */
    @ExcelIgnore
    @TableField(value = "create_by")
    @ApiModelProperty(value="创建者")
    private String createBy;

    /**
     * 更新者
     */
    @ExcelIgnore
    @TableField(value = "update_by")
    @ApiModelProperty(value="更新者")
    private String updateBy;
    @TableId(value = "deleted")
    private Integer deleted;
}
