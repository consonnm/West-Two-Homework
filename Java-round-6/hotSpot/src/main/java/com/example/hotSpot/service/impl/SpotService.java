package com.example.hotSpot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hotSpot.dao.ISpotDao;
import com.example.hotSpot.entity.Spot;
import com.example.hotSpot.service.ISpotService;
import com.example.hotSpot.utils.AliyunOSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SpotService extends ServiceImpl<ISpotDao, Spot> implements ISpotService {
    @Autowired
    AliyunOSSUtil aliyunOSSUtil;
    @Override
    public Spot queryById(int spotId) {
        return getOne(new LambdaQueryWrapper<Spot>()
                .eq(Spot::getSpotId,spotId)
        );
    }

    @Override
    public Boolean update(String spotName, String summary, String detail, String spotSort, int id) {
        Spot spot = baseMapper.selectById(id);
        spot.setSpotId(id);
        spot.setSpotName(spotName);
        spot.setSummary(summary);
        spot.setDetail(detail);
        spot.setSpotSort(spotSort);
        return saveOrUpdate(spot);
    }
    @Override
    public Boolean updatePhoto(MultipartFile file, int id) {
        String url = aliyunOSSUtil.upload(file);
        Spot spot = baseMapper.selectById(id);
        spot.setImage(url);
        spot.setSpotId(id);
        spot.setUserId(spot.getUserId());
        return saveOrUpdate(spot);
    }
    @Override
    public Boolean approved(String approved,int id) {
        Spot spot = baseMapper.selectById(id);
        spot.setApproved(approved);
        spot.setSpotId(id);
        return saveOrUpdate(spot);
    }
    @Override
    public Boolean remove(int id) {
        LambdaQueryWrapper<Spot> lwq = Wrappers.lambdaQuery();
        lwq.eq(Spot::getSpotId,id);
        return remove(lwq);
    }
    @Override
    public int insert(String spotName, String summary, String detail, String spotSort, int userId){
        Spot spot = new Spot();
        spot.setSpotName(spotName);
        spot.setSummary(summary);
        spot.setDetail(detail);
        spot.setSpotSort(spotSort);
        spot.setUserId(userId);
        spot.setApproved("未审核");
        long hour=System.currentTimeMillis()/(1000*60*60);
        spot.setTime(hour);
        save(spot);
        return spot.getSpotId();
    }
    @Override
    public IPage<Spot> findByPage(Page<Spot> page, LambdaQueryWrapper<Spot> userLambdaQueryWrapper){
        return  baseMapper.selectPage(page,userLambdaQueryWrapper);
    }
}
