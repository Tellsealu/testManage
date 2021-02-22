package com.njust.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.njust.domain.ResourceType;
import com.njust.dto.ResourceTypeDto;
import com.njust.mapper.ResourceTypeMapper;
import com.njust.service.ResourceTypeService;
import com.njust.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/1 20:04
 * @Version 1.0
 */
@Service
public class ResourceTypeServiceImpl implements ResourceTypeService {

    @Autowired
    private ResourceTypeMapper resourceTypeMapper;



    @Override
    public DataGridView listResourceTypePage(ResourceTypeDto resourceTypeDto) {
        Page<ResourceType> page=new Page<>(resourceTypeDto.getPageNum(), resourceTypeDto.getPageSize());
        QueryWrapper<ResourceType> qw=new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(resourceTypeDto.getResourceTypeName()), ResourceType.COL_RESOURCE_TYPE_NAME,resourceTypeDto.getResourceTypeName());
        qw.like(StringUtils.isNotBlank(resourceTypeDto.getResourceTypeDescription()),ResourceType.COL_RESOURCE_TYPE_DESCRIPTION,resourceTypeDto.getResourceTypeDescription() );
        qw.orderByAsc(ResourceType.COL_RESOURCE_TYPE_ID);
        this.resourceTypeMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public ResourceType getOne(Integer resourceTypeId) {
        return this.resourceTypeMapper.selectById(resourceTypeId);
    }

    @Override
    public int addResourceType(ResourceTypeDto resourceTypeDto) {
        ResourceType resourceType = new ResourceType();
        BeanUtils.copyProperties(resourceTypeDto, resourceType);
        return this.resourceTypeMapper.insert(resourceType);
    }

    @Override
    public int updateResourceType(ResourceTypeDto resourceTypeDto) {
        ResourceType resourceType = this.resourceTypeMapper.selectById(resourceTypeDto.getResourceTypeId());
        if (null == resourceType) {
            return 0;
        }
        BeanUtils.copyProperties(resourceTypeDto, resourceType);

        return this.resourceTypeMapper.updateById(resourceType);    }

    @Override
    public int deleteResourceTypeByIds(Long[] ResourceIds) {
        List<Long> list = Arrays.asList(ResourceIds);
        return this.resourceTypeMapper.deleteBatchIds(list);
    }




    @Override
    public List<ResourceType> selectAllResourceType() {
        QueryWrapper<ResourceType> qw=new QueryWrapper<>();
        return this.resourceTypeMapper.selectList(qw);
    }

}
