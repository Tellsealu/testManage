package com.njust.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author qufeng
 * @Date 2021/2/17 20:29
 * @Version 1.0
 *
 * 导入，导出用户excel实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    /**
     * 用户姓名
     */
    @TableField(value = "user_name")
    @ExcelProperty(value = "用户姓名",index = 0)
    @ApiModelProperty(value="用户账号")
    private String userName;

    /**
     * 用户性别（0男 1女 2未知）
     */
    @TableField(value = "sex")
    @ExcelProperty(value = "用户性别",index = 2)
    @ApiModelProperty(value="用户性别（0男 1女 2未知）")
    private String sex;

    /**
     * 年龄
     */
    @TableField(value = "age")
    @ExcelProperty(value = "年龄",index = 3)
    @ApiModelProperty(value="年龄")
    private Integer age;

    /**
     * 电话
     */
    @TableField(value = "phone")
    @ExcelProperty(value = "手机号码",index = 1)
    @ApiModelProperty(value="电话")
    private String phone;

    /**
     * 用户邮箱
     */
    @TableField(value = "email")
    @ExcelProperty(value = "用户邮箱",index = 4)
    @ApiModelProperty(value="用户邮箱")
    private String email;

    /**
     * 擅长
     */
    @TableField(value = "strong")
    @ExcelProperty(value = "擅长",index = 5)
    @ApiModelProperty(value="擅长")
    private String strong;

    /**
     * 荣誉
     */
    @TableField(value = "honor")
    @ExcelProperty(value = "荣誉",index = 6)
    @ApiModelProperty(value="荣誉")
    private String honor;

    /**
     * 简介
     */
    @TableField(value = "introduction")
    @ExcelProperty(value = "简介",index = 7)
    @ApiModelProperty(value="简介")
    private String introduction;

}
