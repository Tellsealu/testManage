package com.njust.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.njust.domain.Resource;
import com.njust.dto.ResourceDto;
import com.njust.mapper.ResourceMapper;
import com.njust.service.ResourceService;
import com.njust.utils.IdGeneratorSnowflake;
import com.njust.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/1 20:03
 * @Version 1.0
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;


    @Override
    public DataGridView listResourcePage(ResourceDto resourceDto) {
        Page<Resource> page=new Page<>(resourceDto.getPageNum(), resourceDto.getPageSize());
        QueryWrapper<Resource> qw=new QueryWrapper<>();
        resourceDto.setDeleted(0);

        qw.like(StringUtils.isNotBlank(resourceDto.getResourceName()), Resource.COL_RESOURCE_NAME,resourceDto.getResourceName());
        qw.like(StringUtils.isNotBlank(resourceDto.getResourceContent()), Resource.COL_RESOURCE_CONTENT,resourceDto.getResourceContent());
        qw.like(StringUtils.isNotBlank(resourceDto.getResourceRemark()), Resource.COL_RESOURCE_REMARK,resourceDto.getResourceRemark());

        qw.eq(resourceDto.getResourceTypeId()!=null,Resource.COL_RESOURCE_TYPE_ID,resourceDto.getResourceTypeId());
        qw.eq(Resource.COL_RESOURCE_DELETED,resourceDto.getDeleted());

        qw.ge(null!= resourceDto.getBeginTime(), Resource.COL_CREATE_TIME, resourceDto.getBeginTime());
        qw.le(null!= resourceDto.getEndTime(), Resource.COL_CREATE_TIME, resourceDto.getEndTime());

        qw.orderByAsc(Resource.COL_CREATE_TIME);

        this.resourceMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(), page.getRecords());    }

    @Override
    public Resource getOne(String resourceId) {
        return this.resourceMapper.selectById(resourceId);
    }

    @Override
    public int addResource(ResourceDto resourceDto) {
        Resource resource = new Resource();
        BeanUtils.copyProperties(resourceDto, resource);
        resource.setResourceId(IdGeneratorSnowflake.snowflakeId().toString());
        resource.setCreateTime(DateUtil.date());
        resource.setDeleted(0);
        resource.setCreateBy(resourceDto.getSimpleUser().getUserName());
        return this.resourceMapper.insert(resource);
    }

    @Override
    public int updateResource(ResourceDto resourceDto) {
        Resource resource = this.resourceMapper.selectById(resourceDto.getResourceId());
        if (null == resource) {
            return 0;
        }
        BeanUtils.copyProperties(resourceDto, resource);
        resource.setCreateBy(resourceDto.getSimpleUser().getUserName());
        resource.setUpdateTime(DateUtil.date());
        return this.resourceMapper.updateById(resource);
    }

    @Override
    public int deleteResourceByIds(String[] resourceIds) {
        if (resourceIds.length>1) {
            List<String> list = Arrays.asList(resourceIds);
            for (String id : list) {
                this.resourceMapper.deletedById(id);
            }
        }else {
            String id=resourceIds[0];
            this.resourceMapper.deletedById(id);
        }
        return 1;
    }

    @Override
    public String selectByName(String fileName) {
        return this.resourceMapper.selectByName(fileName);
    }

    @Override
    public String selectByUserExcelName(String fileName) {
        return this.resourceMapper.selectByUserExcelName(fileName);
    }

    @Override
    public String selectByQuestionExcelName(String fileName) {
        return this.resourceMapper.selectByName(fileName);
    }

}

