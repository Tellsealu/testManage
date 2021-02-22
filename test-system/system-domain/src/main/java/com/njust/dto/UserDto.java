package com.njust.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
    * 用户信息表
    */
@ApiModel(value="com-bjsxt-dto-UserDto")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto {
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 用户性别（0男 1女 2未知）
     */
    @NotBlank(message = "用户性别不能为空")
    @ApiModelProperty(value = "用户性别（0男 1女 2未知）")
    private String sex;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private Integer age;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String picture;

    /**
     * 电话
     */
    @NotBlank(message = "用户电话不能为空")
    @ApiModelProperty(value = "电话")
    private String phone;

    /**
     * 用户邮箱
     */
    @ApiModelProperty(value = "用户邮箱")
    private String email;

    /**
     * 擅长
     */
    @ApiModelProperty(value = "擅长")
    private String strong;

    /**
     * 荣誉
     */
    @ApiModelProperty(value = "荣誉")
    private String honor;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介")
    private String introduction;


    /**
     * 帐号状态（0正常 1停用）
     */
    @NotBlank(message = "帐号状态不能为空")
    @ApiModelProperty(value = "帐号状态（0正常 1停用）")
    private String status;

    /**
     * 上次登录时间
     */
    @TableField(value = "last_active_time")
    private Date lastActiveTime;

}