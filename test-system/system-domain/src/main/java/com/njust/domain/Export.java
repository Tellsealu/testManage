package com.njust.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author qufeng
 * @Date 2021/2/22 17:50
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "export")
public class Export extends BaseEntity{

    @TableField("export_id")
    private String expoerId;

    @TableField("download_url")
    private String downloadUrl;

    @TableField("username")
    private String username;

    @TableField("export_time")
    private Date exportTime;
}
