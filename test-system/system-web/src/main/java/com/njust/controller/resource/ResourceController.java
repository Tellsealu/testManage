package com.njust.controller.resource;

import com.njust.aspectj.annotation.Log;
import com.njust.aspectj.enums.BusinessType;
import com.njust.controller.BaseController;
import com.njust.dto.ResourceDto;
import com.njust.service.ResourceService;
import com.njust.utils.ShiroSecurityUtils;
import com.njust.vo.AjaxResult;
import com.njust.vo.DataGridView;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author qufeng
 * @Date 2021/2/3 13:58
 * @Version 1.0
 */
@RestController
@RequestMapping("resource/resource")
public class ResourceController extends BaseController {

    @Reference
    private ResourceService resourceService;


    /**
     * 分页查询
     */
    @GetMapping("listResourceForPage")
    public AjaxResult listResourceForPage(ResourceDto resourceDto){
        DataGridView gridView = this.resourceService.listResourcePage(resourceDto);
        return AjaxResult.success("查询成功",gridView.getData(),gridView.getTotal());
    }

    /**
     * 添加
     */
    @PostMapping("addResource")
    @Log(title = "添加试题",businessType = BusinessType.INSERT)
    public AjaxResult addResource(@Validated ResourceDto resourceDto) {
        resourceDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.resourceService.addResource(resourceDto));
    }

    /**
     * 修改
     */
    @PutMapping("updateResource")
    @Log(title = "修改试题",businessType = BusinessType.UPDATE)
    public AjaxResult updateResource(@Validated ResourceDto resourceDto) {
        resourceDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.resourceService.updateResource(resourceDto));
    }


    /**
     * 根据ID查询一个
     */
    @GetMapping("getResourceById/{resourceId}")
    public AjaxResult getQuestionById(@PathVariable @Validated @NotNull(message = "试题ID不能为空") String resourceId) {
        return AjaxResult.success(this.resourceService.getOne(resourceId));
    }

    /**
     * 删除
     */
    @DeleteMapping("deleteResourceByIds/{resourceIds}")
    @Log(title = "删除试题",businessType = BusinessType.DELETE)
    public AjaxResult deleteQuestionTypeByIds(@PathVariable @Validated @NotEmpty(message = "要删除的ID不能为空") String[] resourceIds) {
        return AjaxResult.toAjax(this.resourceService.deleteResourceByIds(resourceIds));
    }


}