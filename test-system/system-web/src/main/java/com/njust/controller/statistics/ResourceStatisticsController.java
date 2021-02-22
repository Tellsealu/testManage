package com.njust.controller.statistics;

import com.njust.domain.ResourceNum;
import com.njust.service.ResourceNumService;
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
@RequestMapping("statistics/resource")
public class ResourceStatisticsController {


    @Reference
    private ResourceNumService resourceNumService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public AjaxResult selectResourceCount() {

        ResourceNum resourceNum=new ResourceNum();

        //资源总数
        Integer resourceCount=this.resourceNumService.selectResourceCount();

        //资源类型总数
        Integer resourceTypeCount=this.resourceNumService.selectResourceTypeCount();

        //已删除资源数
        Integer resourceDeleted=this.resourceNumService.selectDeleted();

        resourceNum.setResourceCount(resourceCount);
        resourceNum.setResourceDeleted(resourceDeleted);
        resourceNum.setResourceTypeCount(resourceTypeCount);
        List<Integer> mothDayDoExamResourceValue =new ArrayList<>();
        mothDayDoExamResourceValue=this.resourceNumService.selectMonthCount();
        resourceNum.setMothDayDoExamResourceValue(mothDayDoExamResourceValue);

        resourceNum.setMothDayText(DateTimeUtil.MothDay());
        return AjaxResult.success(resourceNum);
    }
}