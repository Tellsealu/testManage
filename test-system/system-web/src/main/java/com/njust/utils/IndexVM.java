package com.njust.utils;

/**
 * @Author qufeng
 * @Date 2021/2/8 14:59
 * @Version 1.0
 */
import lombok.Data;

import java.util.List;
@Data
public class IndexVM {
    private Integer courseCount;
    private Integer questionTypeCount;
    private Integer questionCount;
    private Integer questionDeleted;
    private List<Integer> mothDayUserActionValue;
    private List<Integer> mothDayDoExamQuestionValue;
    private List<String> mothDayText;
}
