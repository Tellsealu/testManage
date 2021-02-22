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
@ApiModel(value="com-njust-domain-QuestionType")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "question_type")
public class QuestionType implements Serializable {

    /**
     * 试题类型ID
     */
    @TableId(value = "question_type_id", type = IdType.INPUT)
    @ApiModelProperty(value="试题类型ID")
    private Integer questionTypeId;

    /**
     * 试题类型名称
     */
    @TableId(value = "question_type_name")
    @ApiModelProperty(value="")
    private String questionTypeName;

    /**
     * 试题类型描述
     */
    @TableId(value = "question_type_description")
    @ApiModelProperty(value="")
    private String questionTypeDescription;

    public static final String COL_QUESTION_TYPE_ID = "question_type_id";

    public static final String COL_QUESTION_TYPE_NAME = "question_type_name";

    public static final String COL_QUESTION_TYPE_DESCRIPTION = "question_type_description";

}
