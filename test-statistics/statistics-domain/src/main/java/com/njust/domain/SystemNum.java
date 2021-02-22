package com.njust.domain;

import lombok.Data;

import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/8 19:27
 * @Version 1.0
 */
@Data
public class SystemNum {

    private Integer userCount;
    private Integer noticeCount;
    private Integer loginCount;
    private Integer operateCount;
    private List<Integer> mothDayDoExamLoginValue;
    private List<Integer> mothDayDoExamOeprateValue;
    private List<String> mothDayText;
    private List<Integer> mothDayUserActionValue;

}
