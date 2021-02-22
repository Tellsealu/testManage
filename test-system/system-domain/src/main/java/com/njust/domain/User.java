package com.njust.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
    * 用户信息表
    */
@ApiModel(value="com-njust-domain-User")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
@JsonIgnoreProperties(value = {"salt","password","unionId","openId"})
public class User extends BaseEntity {
    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    @ApiModelProperty(value="用户ID")
    private Long userId;


    /**
     * 用户账号
     */
    @TableField(value = "user_name")
    @ApiModelProperty(value="用户账号")
    private String userName;

    /**
     * 用户类型（0超级用户为 1为系统用户）
     */
    @TableField(value = "user_type")
    @ApiModelProperty(value="用户类型（0超级用户为 1为系统用户）")
    private String userType;

    /**
     * 用户性别（0男 1女 2未知）
     */
    @TableField(value = "sex")
    @ApiModelProperty(value="用户性别（0男 1女 2未知）")
    private String sex;

    /**
     * 年龄
     */
    @TableField(value = "age")
    @ApiModelProperty(value="年龄")
    private Integer age;

    /**
     * 头像
     */
    @TableField(value = "picture")
    @ApiModelProperty(value="头像")
    private String picture;

    /**
     * 电话
     */
    @TableField(value = "phone")
    @ApiModelProperty(value="电话")
    private String phone;

    /**
     * 用户邮箱
     */
    @TableField(value = "email")
    @ApiModelProperty(value="用户邮箱")
    private String email;

    /**
     * 擅长
     */
    @TableField(value = "strong")
    @ApiModelProperty(value="擅长")
    private String strong;

    /**
     * 荣誉
     */
    @TableField(value = "honor")
    @ApiModelProperty(value="荣誉")
    private String honor;

    /**
     * 简介
     */
    @TableField(value = "introduction")
    @ApiModelProperty(value="简介")
    private String introduction;

    /**
     * 密码
     */
    @TableField(value = "password")
    @ApiModelProperty(value="密码")
    private String password;

    /**
     * 最后一次登录时间
     */
    @TableField(value = "last_login_time")
    @ApiModelProperty(value="最后一次登录时间")
    private Date lastLoginTime;

    /**
     * 最后登陆IP
     */
    @TableField(value = "last_login_ip")
    @ApiModelProperty(value="最后登陆IP")
    private String lastLoginIp;

    /**
     * 帐号状态（0正常 1停用）
     */
    @TableField(value = "status")
    @ApiModelProperty(value="帐号状态（0正常 1停用）")
    private String status;



    /**
     * 上次登录时间
     */
    @TableField(value = "last_active_time")
    private Date lastActiveTime;

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

    /**
     * 盐
     */
    @TableField(value = "salt")
    @ApiModelProperty(value="盐")
    private String salt;



    public static final String COL_USER_ID = "user_id";

    public static final String COL_USER_NAME = "user_name";

    public static final String COL_USER_TYPE = "user_type";

    public static final String COL_SEX = "sex";

    public static final String COL_AGE = "age";

    public static final String COL_PICTURE = "picture";

    public static final String COL_BACKGROUND = "background";

    public static final String COL_PHONE = "phone";

    public static final String COL_EMAIL = "email";

    public static final String COL_STRONG = "strong";

    public static final String COL_HONOR = "honor";

    public static final String COL_INTRODUCTION = "introduction";

    public static final String COL_PASSWORD = "password";

    public static final String COL_LAST_LOGIN_TIME = "last_login_time";

    public static final String COL_LAST_LOGIN_IP = "last_login_ip";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_SALT = "salt";

}