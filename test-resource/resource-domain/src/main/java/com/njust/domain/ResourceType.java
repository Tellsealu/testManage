package com.njust.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author qufeng
 * @Date 2021/2/1 18:55
 * @Version 1.0
 */
@ApiModel(value="com-njust-domain-Resourceype")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "resource_type")
public class ResourceType implements Serializable {

    /**
     * 类型ID
     */
    @TableId(value = "resource_type_id", type = IdType.INPUT)
    @ApiModelProperty(value="类型ID")
    private Integer resourceTypeId;

    /**
     * 类型名称
     */
    @TableId(value = "resource_type_name")
    private String resourceTypeName;

    /**
     * 类型描述
     */
    @TableId(value = "resource_type_description")
    @ApiModelProperty(value="")
    private String resourceTypeDescription;

    public static final String COL_RESOURCE_TYPE_ID = "resource_type_id";

    public static final String COL_RESOURCE_TYPE_NAME = "resource_type_name";

    public static final String COL_RESOURCE_TYPE_DESCRIPTION = "resource_type_description";

}
