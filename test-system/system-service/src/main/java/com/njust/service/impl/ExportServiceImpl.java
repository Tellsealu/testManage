package com.njust.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.njust.domain.Export;
import com.njust.dto.ExportDto;
import com.njust.entity.QuestionEntity;
import com.njust.mapper.ExportMapper;
import com.njust.service.ExportService;
import com.njust.vo.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author qufeng
 * @Date 2021/2/22 18:10
 * @Version 1.0
 */
@Service
public class ExportServiceImpl implements ExportService {

    @Autowired
    private ExportMapper exportMapper;



    @Override
    public int insertExport(Export export) {
        return this.exportMapper.insert(export);
    }

    @Override
    public DataGridView listForPage(ExportDto exportDto) {
        Page<Export> page=new Page<>(exportDto.getPageNum(),exportDto.getPageSize());
        QueryWrapper<Export> qw=new QueryWrapper<>();
        this.exportMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(),page.getRecords());
      }

    @Override
    public int deleteExportByIds(String[] exportIds) {
        return 0;
    }

    @Override
    public int clearExport() {
        return 0;
    }

    @Override
    public List<QuestionEntity> exportAllQuestion(String exportId, String username) {
        List<QuestionEntity> list =exportMapper.selectAllQuestion();
        return list;
        }

    @Override
    public String selectForJson() {
        return this.exportMapper.selectForJson();
    }

    @Override
    public DataGridView listQuestionPage(ExportDto exportDto) {
        Page<Export> page=new Page<>(exportDto.getPageNum(),exportDto.getPageSize());
        QueryWrapper<Export> qw=new QueryWrapper<>();
        this.exportMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(),page.getRecords());
    }


}
