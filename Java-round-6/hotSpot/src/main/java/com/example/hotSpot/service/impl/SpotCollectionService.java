package com.example.hotSpot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hotSpot.dao.ISpotCollectionDao;
import com.example.hotSpot.entity.SpotCollection;
import com.example.hotSpot.service.ISpotCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class SpotCollectionService extends ServiceImpl<ISpotCollectionDao, SpotCollection> implements ISpotCollectionService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public Boolean insert(int userId,int spotId){
        SpotCollection spotCollection = new SpotCollection();
        spotCollection.setUserId(userId);
        spotCollection.setSpotId(spotId);
        long hour=System.currentTimeMillis()/(1000*60*60);
        redisTemplate.opsForZSet().incrementScore("HOUR"+hour,spotId,1);
        return save(spotCollection);
    }
    @Override
    public Boolean remove(int userId,int spotId) {
        LambdaQueryWrapper<SpotCollection> lwq = Wrappers.lambdaQuery();
        lwq.eq(SpotCollection::getUserId,userId).eq(SpotCollection::getSpotId,spotId);
        long hour=System.currentTimeMillis()/(1000*60*60);
        redisTemplate.opsForZSet().incrementScore("HOUR"+hour,spotId,-1);
        return remove(lwq);
    }
    @Override
    public IPage<SpotCollection> findByPage(Page<SpotCollection> page, LambdaQueryWrapper<SpotCollection> userLambdaQueryWrapper){
        return  baseMapper.selectPage(page,userLambdaQueryWrapper);
    }
}
