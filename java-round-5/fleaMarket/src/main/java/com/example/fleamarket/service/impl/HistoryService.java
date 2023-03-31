package com.example.fleamarket.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.fleamarket.dao.IHistoryDao;
import com.example.fleamarket.entity.History;
import com.example.fleamarket.service.IHistoryService;
import org.springframework.stereotype.Service;

import java.awt.print.Book;

@Service
public class HistoryService extends ServiceImpl<IHistoryDao, History> implements IHistoryService {
    @Override
    public Boolean insert(int userId,int goodId){
        History history =new History();
        history.setGoodsId(goodId);
        history.setUserId(userId);
        return save(history);
    }
    @Override
    public Boolean remove(int userId,int goodId) {
        LambdaQueryWrapper<History> lwq = Wrappers.lambdaQuery();
        lwq.eq(History::getUserId,userId).eq(History::getGoodsId,goodId);
        return remove(lwq);
    }
    @Override
    public IPage<History> findByPage(Page<History> page, LambdaQueryWrapper<History> userLambdaQueryWrapper){
        return  baseMapper.selectPage(page,userLambdaQueryWrapper);
    }
    public Boolean find(int userId,int goodId){
        History one = getOne(new LambdaQueryWrapper<History>()
                .eq(History::getGoodsId,goodId).eq(History::getUserId,userId)
        );
        if(ObjectUtils.isNotEmpty(one)){
            return true;
        }
        return false;
    }
}
