package com.njust.service;

import com.njust.domain.Resource;
import com.njust.dto.ResourceDto;
import com.njust.vo.DataGridView;

/**
 * @Author qufeng
 * @Date 2021/2/1 19:17
 * @Version 1.0
 */
public interface ResourceService {
    /**
     * 分页查询
     *
     * @param resourceDto
     * @return
     */
    DataGridView listResourcePage(ResourceDto resourceDto);

    /**
     * 根据ID查询
     *
     * @param resourceId
     * @return
     */
    Resource getOne(String resourceId);

    /**
     * 添加
     *
     * @param resourceDto
     * @return
     */
    int addResource(ResourceDto resourceDto);

    /**
     * 修改
     *
     * @param resourceDto
     * @return
     */
    int updateResource(ResourceDto resourceDto);

    /**
     * 根据ID删除
     *
     * @param resourceIds
     * @return
     */
    int deleteResourceByIds(String[] resourceIds);

    String selectByName(String fileName);

    String selectByUserExcelName(String fileName);

    String selectByQuestionExcelName(String fileName);

    Integer selectResourceCount(Long courseId);

    Integer seleceResourceCount(Long resourceTypeId);
}
