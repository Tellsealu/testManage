package com.njust.service;

import com.njust.domain.ResourceType;
import com.njust.dto.ResourceTypeDto;
import com.njust.vo.DataGridView;

import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/1 19:17
 * @Version 1.0
 */
public interface ResourceTypeService {
    /**
     * 分页查询
     *
     * @param resourceTypeDto
     * @return
     */
    DataGridView listResourceTypePage(ResourceTypeDto resourceTypeDto);

    /**
     * 根据ID查询
     *
     * @param resourceTypeId
     * @return
     */
    ResourceType getOne(Integer resourceTypeId);

    /**
     * 添加
     *
     * @param resourceTypeDto
     * @return
     */
    int addResourceType(ResourceTypeDto resourceTypeDto);

    /**
     * 修改
     *
     * @param resourceTypeDto
     * @return
     */
    int updateResourceType(ResourceTypeDto resourceTypeDto);

    /**
     * 根据ID删除
     *
     * @param QuestionTypeIds
     * @return
     */
    int deleteResourceTypeByIds(Long[] QuestionTypeIds);

    List<ResourceType> selectAllResourceType();
}
