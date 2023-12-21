package com.example.fleamarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.fleamarket.dao.IAnnouncementDao;
import com.example.fleamarket.entity.Announcement;
import com.example.fleamarket.service.IAnnouncementService;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementService extends ServiceImpl<IAnnouncementDao, Announcement> implements IAnnouncementService {
    @Override
    public Boolean insert(String context,String topic,String time){
        Announcement announcement = new Announcement();
        announcement.setContext(context);
        announcement.setTime(time);
        announcement.setTopic(topic);
        return save(announcement);
    }
    @Override
    public Boolean remove(int announcementId) {
        LambdaQueryWrapper<Announcement> lwq = Wrappers.lambdaQuery();
        lwq.eq(Announcement::getAnnouncementId,announcementId);
        return remove(lwq);
    }
    @Override
    public IPage<Announcement> findByPage(Page<Announcement> page,LambdaQueryWrapper<Announcement> userLambdaQueryWrapper){
        return  baseMapper.selectPage(page,userLambdaQueryWrapper);
    }
}
