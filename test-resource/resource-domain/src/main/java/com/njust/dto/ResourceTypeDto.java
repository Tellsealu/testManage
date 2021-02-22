package com.njust.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author qufeng
 * @Date 2021/2/1 19:09
 * @Version 1.0
 */
@ApiModel(value="com-njust-domain-QuestionType")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "question_type")
public class ResourceTypeDto extends BaseDto{
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
}
