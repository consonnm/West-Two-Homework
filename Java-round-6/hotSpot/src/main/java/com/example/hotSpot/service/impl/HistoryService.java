package com.example.hotSpot.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hotSpot.dao.IHistoryDao;
import com.example.hotSpot.entity.History;
import com.example.hotSpot.service.IHistoryService;
import org.springframework.stereotype.Service;

@Service
public class HistoryService extends ServiceImpl<IHistoryDao, History> implements IHistoryService {
    @Override
    public Boolean insert(int userId,int goodId){
        History history =new History();
        history.setSpotId(goodId);
        history.setUserId(userId);
        return save(history);
    }
    @Override
    public Boolean remove(int userId,int goodId) {
        LambdaQueryWrapper<History> lwq = Wrappers.lambdaQuery();
        lwq.eq(History::getUserId,userId).eq(History::getSpotId,goodId);
        return remove(lwq);
    }
    @Override
    public IPage<History> findByPage(Page<History> page, LambdaQueryWrapper<History> userLambdaQueryWrapper){
        return  baseMapper.selectPage(page,userLambdaQueryWrapper);
    }
    @Override
    public Boolean find(int userId,int goodId){
        History one = getOne(new LambdaQueryWrapper<History>()
                .eq(History::getSpotId,goodId).eq(History::getUserId,userId)
        );
        if(ObjectUtils.isNotEmpty(one)){
            return true;
        }
        return false;
    }
}
