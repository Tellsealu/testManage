package com.njust.utils;

/**
 * @Author qufeng
 * @Date 2021/2/22 16:57
 * @Version 1.0
 * 异步执行任务
 * 1、拨测的时候更新拨测号码的标记信息
 * 2、选号的时候根据企业id修改名称
 * 3、目的号码管理-导出全部
 * 4、热线号码导出
 */
public enum AsyncTask {
    UPDATE_NUMBER_REMARK("1"),
    UPDATE_ENTERPRISE_NAME_By_ENTERPRISE_Id("2"),
    EXPORT_ALL_TRUNK("3"),
    EXPORT_HOTLINE_NUMBER("4"),
    EXPORT_ALL_QUESTION("5"),
    EXPORT_ALL_USER("6");

    private final String no;

    AsyncTask(String no) {
        this.no = no;
    }

    public String getNo(){
        return no;
    }

    public static AsyncTask getAsyncTask(String no){
        for (AsyncTask value : AsyncTask.values()) {
            if(value.getNo().equals(no)){
                return value;
            }
        }
        return null;
    }
}
