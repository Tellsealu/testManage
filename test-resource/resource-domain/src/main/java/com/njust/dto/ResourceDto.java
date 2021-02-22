package com.njust.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class ResourceDto extends BaseDto{


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
      */
    @TableId(value = "url")
    private String url;

    @TableId(value = "deleted")
    private Integer deleted;


}
