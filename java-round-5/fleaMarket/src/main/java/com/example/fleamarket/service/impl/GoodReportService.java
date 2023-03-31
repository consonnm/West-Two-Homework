package com.example.fleamarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.fleamarket.dao.IGoodReportDao;
import com.example.fleamarket.entity.GoodReport;
import com.example.fleamarket.service.IGoodReportService;
import org.springframework.stereotype.Service;

@Service
public class GoodReportService extends ServiceImpl<IGoodReportDao, GoodReport> implements IGoodReportService {
    @Override
    public Boolean insert(String context,int goodId){
        GoodReport goodReport = new GoodReport();
        goodReport.setGoodId(goodId);
        goodReport.setContext(context);
        goodReport.setStatus("未审核");
        return save(goodReport);
    }
    @Override
    public IPage<GoodReport> findByPage(Page<GoodReport> page, LambdaQueryWrapper<GoodReport> userLambdaQueryWrapper){
        return  baseMapper.selectPage(page,userLambdaQueryWrapper);
    }
    @Override
    public Boolean update(String status, int reportId){
        GoodReport goodReport = baseMapper.selectById(reportId);
        goodReport.setReportId(reportId);
        goodReport.setStatus(status);
        return saveOrUpdate(goodReport);
    }

}
