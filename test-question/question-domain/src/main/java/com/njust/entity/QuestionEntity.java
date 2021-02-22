package com.njust.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author qufeng
 * @Date 2021/2/1 18:55
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionEntity implements Serializable {


    /**
     * 试题名称
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "试题名称",index = 0)
    private String questionName;

    /**
     * 试题分数
     */
    @ColumnWidth(20)
    @ExcelProperty(value = "试题分数",index = 1)
    private Integer questionScore;

    /**
     * 试题类型名称
     */
    @ColumnWidth(20)
    @ExcelProperty(value = "试题类型",index = 2)
    private String questionTypeName;

    /**
     * 试题答案
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "试题答案",index = 3)
    private String questionAnswer;


    /**
     * 课程
     */
    @ColumnWidth(20)
    @ExcelProperty(value = "所属课程",index = 4)
    private String courseName;

    /**
     * 额外描述
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "试题描述",index = 5)
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
}
