package com.njust.controller.resource;

import com.njust.aspectj.annotation.Log;
import com.njust.aspectj.enums.BusinessType;
import com.njust.controller.BaseController;
import com.njust.dto.ResourceTypeDto;
import com.njust.service.ResourceTypeService;
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
@RequestMapping("resource/resourcetype")
public class ResourceTypeController extends BaseController {

    @Reference
    private ResourceTypeService resourceTypeService;


    /**
     * 分页查询
     */
    @GetMapping("listResourceTypeForPage")
    public AjaxResult listResourceTypeForPage(ResourceTypeDto resourceTypeDto){
        DataGridView gridView = this.resourceTypeService.listResourceTypePage(resourceTypeDto);
        return AjaxResult.success("查询成功",gridView.getData(),gridView.getTotal());
    }

    /**
     * 添加
     */
    @PostMapping("addResourceType")
    @Log(title = "添加试题",businessType = BusinessType.INSERT)
    public AjaxResult addResourceType(@Validated ResourceTypeDto resourceTypeDto) {
        return AjaxResult.toAjax(this.resourceTypeService.addResourceType(resourceTypeDto));
    }

    /**
     * 修改
     */
    @PutMapping("updateResourceType")
    @Log(title = "修改试题",businessType = BusinessType.UPDATE)
    public AjaxResult updateResourceType(@Validated ResourceTypeDto resourceTypeDto) {
        resourceTypeDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.resourceTypeService.updateResourceType(resourceTypeDto));
    }


    /**
     * 根据ID查询一个
     */
    @GetMapping("getResourceTypeById/{resourceTypeId}")
    public AjaxResult getResourceTypeById(@PathVariable @Validated @NotNull(message = "试题ID不能为空") Integer resourceTypeId) {
        return AjaxResult.success(this.resourceTypeService.getOne(resourceTypeId));
    }

    /**
     * 删除
     */
    @DeleteMapping("deleteResourceTypeByIds/{resourceTypeIds}")
    @Log(title = "删除试题",businessType = BusinessType.DELETE)
    public AjaxResult deleteResourceTypeByIds(@PathVariable @Validated @NotEmpty(message = "要删除的ID不能为空") Long[] resourceTypeIds) {
        return AjaxResult.toAjax(this.resourceTypeService.deleteResourceTypeByIds(resourceTypeIds));
    }
    /**
     * 查询所有类型
     */
    @GetMapping("selectAllResourceType")
    public AjaxResult selectAllResourceType(){
        return AjaxResult.success(this.resourceTypeService.selectAllResourceType());
    }

}