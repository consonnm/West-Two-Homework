package com.example.hotSpot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hotSpot.dao.ITimeLineDao;
import com.example.hotSpot.entity.TimeLine;
import com.example.hotSpot.service.ITimeLineService;

import org.springframework.stereotype.Service;

@Service
public class TimeLineLineService extends ServiceImpl<ITimeLineDao, TimeLine> implements ITimeLineService {
    @Override
    public Boolean baseUpdate(int spotId,String time,String describe,int timeId) {
        TimeLine timeLine1 = baseMapper.selectById(timeId);
        timeLine1.setSpotId(spotId);
        timeLine1.setTimeNow(time);
        timeLine1.setTimeDescribe(describe);
        return saveOrUpdate(timeLine1);
    }
    @Override
    public int insert(int spotId,String time,String describe){
        TimeLine timeLine1=new TimeLine();
        timeLine1.setSpotId(spotId);
        timeLine1.setTimeNow(time);
        timeLine1.setTimeDescribe(describe);
        save(timeLine1);
        return timeLine1.getTimeId();
    }
    @Override
    public IPage<TimeLine> findByPage(Page<TimeLine> page, LambdaQueryWrapper<TimeLine> userLambdaQueryWrapper){
        return  baseMapper.selectPage(page,userLambdaQueryWrapper);
    }
    @Override
    public Boolean remove(int timeId) {
        LambdaQueryWrapper<TimeLine> lwq = Wrappers.lambdaQuery();
        lwq.eq(TimeLine::getTimeId,timeId);
        return remove(lwq);
    }
}
