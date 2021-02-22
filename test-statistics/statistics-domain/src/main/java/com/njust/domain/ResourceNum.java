package com.njust.domain;

import lombok.Data;

import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/8 19:27
 * @Version 1.0
 */
@Data
public class ResourceNum {
    private Integer resourceCount;
    private Integer resourceTypeCount;
    private Integer resourceDeleted;
    private List<Integer> mothDayDoExamResourceValue;
    private List<String> mothDayText;

}
