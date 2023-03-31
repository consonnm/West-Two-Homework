package com.example.hotSpot.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hotSpot.dao.IFollowDao;
import com.example.hotSpot.entity.Follow;
import com.example.hotSpot.service.IFollowService;
import org.springframework.stereotype.Service;

@Service
public class FollowService extends ServiceImpl<IFollowDao, Follow> implements IFollowService {
	@Override
	public Boolean insert(int followerId,int followedId){
		Follow follow = new Follow();
		follow.setFollowedId(followedId);
		follow.setFollowerId(followerId);
		return save(follow);
	}
	@Override
	public Boolean remove(int followerId,int followedId) {
		LambdaQueryWrapper<Follow> lwq = Wrappers.lambdaQuery();
		lwq.eq(Follow::getFollowerId,followerId).eq(Follow::getFollowedId,followedId);
		return remove(lwq);
	}
	@Override
	public IPage<Follow> findByPage(Page<Follow> page, LambdaQueryWrapper<Follow> userLambdaQueryWrapper){
		return  baseMapper.selectPage(page,userLambdaQueryWrapper);
	}
}
