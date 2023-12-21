package com.example.fleamarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.fleamarket.dao.IAuditDao;
import com.example.fleamarket.dao.ICategoryDao;
import com.example.fleamarket.entity.Audit;
import com.example.fleamarket.entity.Category;
import com.example.fleamarket.service.IAuditService;
import com.example.fleamarket.service.ICategoryService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Service;

@Service
public class AuditService extends ServiceImpl<IAuditDao, Audit> implements IAuditService {
    @Override
    public Boolean insert(Boolean status,int goodId,int adminId){
        Audit audit = new Audit();
        audit.setStatus(status);
        audit.setGoodId(goodId);
        audit.setAdminId(adminId);
        return save(audit);
    }
    @Override
    public IPage<Audit> findByPage(Page<Audit> page, LambdaQueryWrapper<Audit> userLambdaQueryWrapper){
        return  baseMapper.selectPage(page,userLambdaQueryWrapper);
    }
}
