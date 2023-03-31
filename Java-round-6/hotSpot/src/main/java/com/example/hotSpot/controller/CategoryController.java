package com.example.hotSpot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotSpot.entity.Category;
import com.example.hotSpot.exception.ControllerException;
import com.example.hotSpot.response.ResultVo;
import com.example.hotSpot.service.ICategoryService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value ="/category")
public class CategoryController {
    @Autowired
    ICategoryService iCategoryService;
    @ApiOperation("查询所有分类")
    @GetMapping("/getAllFollow")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current",value = "当前页"),
            @ApiImplicitParam(name="size",value="大小"),
    })
    public ResultVo all(int current,int size){
        log.info("查询所有分类");
        Page<Category> page = new Page<>(current,size);
        return new ResultVo().setCode(200).setData(iCategoryService.findByPage(page,null));
    }
    //@RequiresRoles("admin")
    @ApiOperation("增加分类")
    @PostMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodSort",value = "分类名"),
            @ApiImplicitParam(name="spotDescribe",value="描述"),
    })
    public ResultVo insert(String goodSort,String spotDescribe){
        log.info("增加分类");
        if(goodSort!=null){
            return new ResultVo().setCode(200).setData(iCategoryService.insert(goodSort,spotDescribe));}
        else throw new  ControllerException("分类不可为null");
    }
    //@RequiresRoles("admin")
    @ApiOperation("删除分类")
    @DeleteMapping("/remove")
    @ApiImplicitParam(name = "id",value = "分类Id")
    public ResultVo remove(int id){
        log.info("删除分类");
        if(iCategoryService.remove(id)){
            return new ResultVo().setCode(200);}
        else throw new ControllerException("此公告不存在");
    }
}
