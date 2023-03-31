package com.example.hotSpot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hotSpot.dao.IAuditDao;
import com.example.hotSpot.entity.Audit;
import com.example.hotSpot.service.IAuditService;
import org.springframework.stereotype.Service;

@Service
public class AuditService extends ServiceImpl<IAuditDao, Audit> implements IAuditService {
    @Override
    public Boolean insert(Boolean status,int goodId,int adminId){
        Audit audit = new Audit();
        audit.setStatus(status);
        audit.setId(goodId);
        audit.setAdminId(adminId);
        return save(audit);
    }
    @Override
    public IPage<Audit> findByPage(Page<Audit> page, LambdaQueryWrapper<Audit> userLambdaQueryWrapper){
        return  baseMapper.selectPage(page,userLambdaQueryWrapper);
    }
}
