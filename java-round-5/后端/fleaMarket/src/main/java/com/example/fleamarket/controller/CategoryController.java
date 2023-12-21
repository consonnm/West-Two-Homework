package com.example.fleamarket.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fleamarket.entity.Category;
import com.example.fleamarket.exception.ControllerException;
import com.example.fleamarket.response.ResultVo;
import com.example.fleamarket.service.ICategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value ="/catgory")
public class CategoryController {
    @Autowired
    ICategoryService iCategoryService;
    @ApiOperation("查询所有分类")
    @GetMapping("/getAllFollow")
    public ResultVo all(@ApiParam("当前页")int current,@ApiParam("大小") int size){
        log.info("查询所有分类");
        Page<Category> page = new Page<>(current,size);
        return new ResultVo().setData(iCategoryService.findByPage(page,null));
    }
    @RequiresRoles("admin")
    @ApiOperation("增加分类")
    @GetMapping("/insert")
    public ResultVo insert(@ApiParam("分类名")String goodSort,@ApiParam("描述")String goodDescribe){
        log.info("增加分类");
        if(goodSort!=null){
        return new ResultVo().setData(iCategoryService.insert(goodSort,goodDescribe));}
        else throw new  ControllerException("分类不可为null");
    }
    @RequiresRoles("admin")
    @ApiOperation("删除分类")
    @GetMapping("/remove")
    public ResultVo remove(@ApiParam("分类id")int id){
        log.info("删除分类");
        if(iCategoryService.remove(id)==true){
            return new ResultVo().setCode(200);}
        else throw new ControllerException("此公告不存在");
    }
}
