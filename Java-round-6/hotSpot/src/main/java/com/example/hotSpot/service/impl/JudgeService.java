package com.example.hotSpot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hotSpot.dao.IJudgeDao;
import com.example.hotSpot.entity.Judge;
import com.example.hotSpot.service.IJudgeService;
import com.example.hotSpot.utils.AliyunOSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class JudgeService extends ServiceImpl<IJudgeDao, Judge> implements IJudgeService {
    @Autowired
    AliyunOSSUtil aliyunOSSUtil;
    @Override
    public Judge queryById(int id) {
        return getOne(new LambdaQueryWrapper<Judge>()
                .eq(Judge::getJudgeId,id)
        );
    }

    @Override
    public Boolean update(String goodName, String summary, String detail,int spotId,int id) {
        Judge judge = baseMapper.selectById(id);
        judge.setSpotId(id);
        judge.setJudgeName(goodName);
        judge.setSummary(summary);
        judge.setDetail(detail);
        judge.setSpotId(spotId);
        return saveOrUpdate(judge);
    }
    @Override
    public Boolean updatePhoto(MultipartFile file,int id) {
        String url = aliyunOSSUtil.upload(file);
        Judge judge = baseMapper.selectById(id);
        judge.setImage(url);
        judge.setSpotId(id);
        judge.setUserId(judge.getUserId());
        return saveOrUpdate(judge);
    }
    @Override
    public Boolean approved(String approved,int id) {
        Judge judge = baseMapper.selectById(id);
        judge.setApproved(approved);
        return saveOrUpdate(judge);
    }
    @Override
    public Boolean remove(int goodId) {
        LambdaQueryWrapper<Judge> lwq = Wrappers.lambdaQuery();
        lwq.eq(Judge::getSpotId,goodId);
        return remove(lwq);
    }
    @Override
    public int insert(String name,String summary,String detail,int spotId,int userId){
        Judge judge = new Judge();
        judge.setJudgeName(name);
        judge.setSummary(summary);
        judge.setDetail(detail);
        judge.setSpotId(spotId);
        judge.setUserId(userId);
        judge.setApproved("未审核");
        save(judge);
        return judge.getSpotId();
    }
    @Override
    public IPage<Judge> findByPage(Page<Judge> page, LambdaQueryWrapper<Judge> userLambdaQueryWrapper){
        return  baseMapper.selectPage(page,userLambdaQueryWrapper);
    }
    @Override
    public Boolean change(int flag,int judgeId) {
        Judge judge = baseMapper.selectById(judgeId);
        if(flag==1) judge.setPositive(judge.getPositive()+1);
        else judge.setNegative(judge.getNegative()+1);
        return saveOrUpdate(judge);
    }

}
