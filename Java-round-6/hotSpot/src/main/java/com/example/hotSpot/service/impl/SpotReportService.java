package com.example.hotSpot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hotSpot.dao.ISpotReportDao;
import com.example.hotSpot.entity.SpotReport;
import com.example.hotSpot.service.ISpotReportService;
import org.springframework.stereotype.Service;

@Service
public class SpotReportService extends ServiceImpl<ISpotReportDao, SpotReport> implements ISpotReportService {
    @Override
    public Boolean insert(String context,int goodId){
        SpotReport spotReport = new SpotReport();
        spotReport.setSpotId(goodId);
        spotReport.setContext(context);
        spotReport.setStatus("未审核");
        return save(spotReport);
    }
    @Override
    public IPage<SpotReport> findByPage(Page<SpotReport> page, LambdaQueryWrapper<SpotReport> userLambdaQueryWrapper){
        return  baseMapper.selectPage(page,userLambdaQueryWrapper);
    }
    @Override
    public Boolean update(String status, int reportId){
        SpotReport spotReport = baseMapper.selectById(reportId);
        spotReport.setReportId(reportId);
        spotReport.setStatus(status);
        return saveOrUpdate(spotReport);
    }

}
