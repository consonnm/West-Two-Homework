package com.example.hotSpot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hotSpot.dao.ICategoryDao;
import com.example.hotSpot.entity.Category;
import com.example.hotSpot.service.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService extends ServiceImpl<ICategoryDao, Category> implements ICategoryService {
    public int insert(String goodSort,String describe){
        Category category = new Category();
        category.setSpotSort(goodSort);
        category.setSpotDescribe(describe);
        save(category);
        return category.getCategoryId();
    }
    @Override
    public Boolean remove(int id) {
        LambdaQueryWrapper<Category> lwq = Wrappers.lambdaQuery();
        lwq.eq(Category::getCategoryId,id);
        return remove(lwq);
    }
    @Override
    public IPage<Category> findByPage(Page<Category> page, LambdaQueryWrapper<Category> userLambdaQueryWrapper){
        return  baseMapper.selectPage(page,userLambdaQueryWrapper);
    }



}
