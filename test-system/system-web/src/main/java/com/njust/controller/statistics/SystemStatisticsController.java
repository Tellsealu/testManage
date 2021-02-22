package com.njust.controller.statistics;

import com.njust.domain.SystemNum;
import com.njust.service.SystemNumService;
import com.njust.utils.DateTimeUtil;
import com.njust.vo.AjaxResult;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/8 22:03
 * @Version 1.0
 */
@RestController
@RequestMapping("statistics/system")
public class SystemStatisticsController {


    @Reference
    private SystemNumService systemNumService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public AjaxResult selectSystemCount() {

        SystemNum systemNum=new SystemNum();


        //用户总数
        Integer userCount=this.systemNumService.selectUserCount();

        //公告总数
        Integer noticeCount=this.systemNumService.selectNoticeTypeCount();

        //登陆总数
        Integer loginCount=this.systemNumService.selectLoginCount();

        //操作总数
        Integer operateCount=this.systemNumService.selectOperateCount();

        systemNum.setUserCount(userCount);
        systemNum.setLoginCount(loginCount);
        systemNum.setNoticeCount(noticeCount);
        systemNum.setOperateCount(operateCount);
        //月活跃数
        List<Integer> mothDayUserActionValue = new ArrayList<>();
        //每天操作数量
        List<Integer> mothDayDoExamOeprateValue =new ArrayList<>();


        mothDayUserActionValue=this.systemNumService.selectUserAction();

        mothDayDoExamOeprateValue=this.systemNumService.selectOperateAction();

        systemNum.setMothDayUserActionValue(mothDayUserActionValue);
        systemNum.setMothDayDoExamOeprateValue(mothDayDoExamOeprateValue);

        systemNum.setMothDayText(DateTimeUtil.MothDay());
        return AjaxResult.success(systemNum);
    }
}