package com.example.hotSpot.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hotSpot.entity.Spot;
import org.springframework.web.multipart.MultipartFile;

public interface ISpotService extends IService<Spot> {
    Spot queryById(int spotId);

    Boolean update(String spotName, String summary, String detail,String spotSort,int spotId);

    Boolean updatePhoto(MultipartFile file, int spotId);

    int insert(String spotName,String summary,String detail,String spotSort,int userId);

    IPage<Spot> findByPage(Page<Spot> page, LambdaQueryWrapper<Spot> userLambdaQueryWrapper);

    Boolean approved(String approved,int id);

    Boolean remove(int id);
}
