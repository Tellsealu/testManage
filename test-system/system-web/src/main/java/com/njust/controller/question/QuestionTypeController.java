package com.njust.controller.question;

import com.njust.aspectj.annotation.Log;
import com.njust.aspectj.enums.BusinessType;
import com.njust.controller.BaseController;
import com.njust.dto.QuestionTypeDto;
import com.njust.service.QuestionTypeService;
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
 * @Date 2021/2/2 17:20
 * @Version 1.0
 */
@RestController
@RequestMapping("question/questiontype")
public class QuestionTypeController extends BaseController {

    @Reference
    private QuestionTypeService questionTypeService;



    /**
     * 分页查询
     */
    @GetMapping("listQuestionTypeForPage")
    public AjaxResult listQuestionTypeForPage(QuestionTypeDto questionTypeDto){
        DataGridView gridView = this.questionTypeService.listQuestionTypePage(questionTypeDto);
        return AjaxResult.success("查询成功",gridView.getData(),gridView.getTotal());
    }

    /**
     * 添加
     */
    @PostMapping("addQuestionType")
    @Log(title = "添加试题类型",businessType = BusinessType.INSERT)
    public AjaxResult addQuestionType(@Validated QuestionTypeDto questionTypeDto) {
        return AjaxResult.toAjax(this.questionTypeService.addQuestionType(questionTypeDto));
    }

    /**
     * 修改
     */
    @PutMapping("updateQuestionType")
    @Log(title = "修改试题类型",businessType = BusinessType.UPDATE)
    public AjaxResult updateQuestionType(@Validated QuestionTypeDto questionTypeDto) {
        questionTypeDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.questionTypeService.updateQuestionType(questionTypeDto));
    }


    /**
     * 根据ID查询一个用户信息
     */
    @GetMapping("getQuestionTypeById/{questionTypeId}")
    public AjaxResult getQuestionTypeById(@PathVariable @Validated @NotNull(message = "试题ID不能为空") Integer questionTypeId) {
        return AjaxResult.success(this.questionTypeService.getOne(questionTypeId));
    }

    /**
     * 删除
     */
    @DeleteMapping("deleteQuestionTypeByIds/{questionTypeIds}")
    @Log(title = "删除试题类型",businessType = BusinessType.DELETE)
    public AjaxResult deleteQuestionTypeByIds(@PathVariable @Validated @NotEmpty(message = "要删除的ID不能为空") Long[] questionTypeIds) {
        return AjaxResult.toAjax(this.questionTypeService.deleteQuestionTypeByIds(questionTypeIds));
    }
    /**
     * 查询所有类型
     */
    @GetMapping("selectAllQuestionType")
    public AjaxResult selectAllQuestionType(){
        return AjaxResult.success(this.questionTypeService.selectAllQuestionType());
    }
}
