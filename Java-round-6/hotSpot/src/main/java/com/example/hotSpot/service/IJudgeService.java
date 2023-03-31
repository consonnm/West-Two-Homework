package com.example.hotSpot.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hotSpot.entity.Judge;
import org.springframework.web.multipart.MultipartFile;

public interface IJudgeService extends IService<Judge> {
    Judge queryById(int goodId);

    Boolean update(String goodName, String summary, String detail,int spotId,int id);

    Boolean updatePhoto(MultipartFile file, int id);

    Boolean remove(int id);

    int insert(String name,String summary,String detail,int spotId,int userId);

    IPage<Judge> findByPage(Page<Judge> page, LambdaQueryWrapper<Judge> userLambdaQueryWrapper);

    Boolean approved(String approved,int goodId);

    Boolean change(int flag,int goodId);

}

