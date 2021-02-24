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
 * @Date 2021/2/1 18:55
 * @Version 1.0
 */
@ApiModel(value="com-njust-domain-Resource")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "resource")
public class Resource implements Serializable {

    /**
     * 资源ID
     */
    @TableId(value = "resource_id", type = IdType.INPUT)
    @ApiModelProperty(value="ID 全局ID雪花算法")
    private String resourceId;

    /**
     * 名称
     */
    @TableId(value = "resource_name")
    @ApiModelProperty(value="名称")
    private String resourceName;


    /**
     * 类型
     */
    @TableId(value = "resource_type_id")
    @ApiModelProperty(value="类型")
    private Integer resourceTypeId;

    /**
     * [json表示]
     */
    @TableField(value = "resource_url")
    @ApiModelProperty(value="[json表示]")
    private String resourceUrl;

    /**
     * 资源内容
     */
    @TableId(value = "resource_content")
    private String resourceContent;

    /**
     * 额外描述
     */
    @TableId(value = "resource_remark")
    private String resourceRemark;

    /*
    * URL
    * */
    @TableId(value = "url")
    private String url;

    /**
     * 是否删除
     */
    @TableId(value = "deleted")
    private Integer deleted;

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

    @TableField(value = "resource_course_id")
    private Integer resourceCourseId;

    public static final String COL_RESOURCE_COURSE_ID="resource_course_id";

    public static final String COL_RESOURCE_ID = "resource_id";

    public static final String COL_RESOURCE_NAME = "resource_name";

    public static final String COL_RESOURCE_TYPE_ID = "resource_type_id";

    public static final String COL_RESOURCE_URL = "resource_url";

    public static final String COL_RESOURCE_CONTENT = "resource_content";

    public static final String COL_RESOURCE_REMARK = "resource_remark";

    public static final String COL_RESOURCE_DELETED = "deleted";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_UPDATE_BY = "update_by";
}
