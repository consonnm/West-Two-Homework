package com.example.fleamarket.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.fleamarket.entity.Goods;
import org.springframework.web.multipart.MultipartFile;

public interface IGoodService extends IService<Goods> {
    Goods queryById(int goodId);

    Boolean update(String goodName, String summary, String detail, double price, String goodSort, int goodId);

    Boolean updatePhoto(MultipartFile file, int goodId);

    Boolean remove(int goodId);

    int insert(String goodName, String summary, String detail, double price, String goodSort, int userId);

    IPage<Goods> findByPage(Page<Goods> page, LambdaQueryWrapper<Goods> userLambdaQueryWrapper);

    Boolean approved(String approved,int goodId);

    Boolean isSoldUpdate(int goodId);
}

