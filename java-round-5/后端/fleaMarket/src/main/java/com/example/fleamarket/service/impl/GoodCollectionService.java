package com.example.fleamarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.fleamarket.dao.IGoodsCollectionDao;
import com.example.fleamarket.entity.GoodsCollection;
import com.example.fleamarket.service.IGoodsCollectionService;
import org.springframework.stereotype.Service;

@Service
public class GoodCollectionService extends ServiceImpl<IGoodsCollectionDao, GoodsCollection> implements IGoodsCollectionService{
    @Override
    public Boolean insert(int userId,int goodId){
        GoodsCollection goodsCollection = new GoodsCollection();
        goodsCollection.setUserId(userId);
        goodsCollection.setGoodId(goodId);
        return save(goodsCollection);
    }
    @Override
    public Boolean remove(int userId,int goodId) {
        LambdaQueryWrapper<GoodsCollection> lwq = Wrappers.lambdaQuery();
        lwq.eq(GoodsCollection::getUserId,userId).eq(GoodsCollection::getGoodId,goodId);
        return remove(lwq);
    }
    @Override
    public IPage<GoodsCollection> findByPage(Page<GoodsCollection> page, LambdaQueryWrapper<GoodsCollection> userLambdaQueryWrapper){
        return  baseMapper.selectPage(page,userLambdaQueryWrapper);
    }
}
