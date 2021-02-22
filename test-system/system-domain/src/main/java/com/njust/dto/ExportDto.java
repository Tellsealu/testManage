package com.njust.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author qufeng
 * @Date 2021/2/22 18:05
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportDto extends BaseDto {

    @TableField("export_id")
    private String expoerId;

    @TableField("download_url")
    private String downloadUrl;

    @TableField("username")
    private String username;

    @TableField("export_time")
    private String exportTime;
}