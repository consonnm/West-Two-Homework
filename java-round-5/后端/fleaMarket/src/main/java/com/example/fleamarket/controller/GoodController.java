package com.example.fleamarket.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fleamarket.entity.Goods;
import com.example.fleamarket.entity.User;
import com.example.fleamarket.response.ResultVo;
import com.example.fleamarket.service.IGoodService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping(value ="/good")
public class GoodController {
    @Autowired
    IGoodService iGoodService;
    @RequiresRoles("user::user")
    @ApiOperation("模糊查询商品")
    @GetMapping("/getGood")
    public ResultVo list(@ApiParam("商品名称") String goodName,@ApiParam("当前页")int current,@ApiParam("大小")int size) {
        log.info("模糊查询商品");
        Page<Goods> page = new Page<>(current , size );
        LambdaQueryWrapper<Goods> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.like(Goods::getGoodName, goodName);
        return new ResultVo().setData(iGoodService.findByPage(page,userLambdaQueryWrapper));
    }
    @RequiresRoles("user::user")
    @ApiOperation("查询所有商品")
    @GetMapping("/getAllGood")
    public ResultVo all(@ApiParam("当前页")int current,@ApiParam("大小")int size) {
        log.info("查询所有商品");
        Page<Goods> page = new Page<>(current , size );
        return new ResultVo().setData(iGoodService.findByPage(page,null));
    }
    @RequiresRoles("user::user")
    @ApiOperation("根据类别查询商品")
    @GetMapping("/getGoodBySort")
    public ResultVo sort(@ApiParam("商品类别") String goodSort,@ApiParam("当前页")int current,@ApiParam("大小")int size) {
        log.info("根据类别查询商品");
        Page<Goods> page = new Page<>(current,size);
        LambdaQueryWrapper<Goods> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(Goods::getGoodSort, goodSort);
        return new ResultVo().setData(iGoodService.findByPage(page,userLambdaQueryWrapper));
    }
    @RequiresRoles("user::user")
    @ApiOperation("根据类别及关键字查询商品")
    @GetMapping("/getGoodBySortByKey")
    public ResultVo sortByKey(@ApiParam("商品类别") String goodSort,@ApiParam("商品关键字")String goodName,@ApiParam("当前页")int current,@ApiParam("大小")int size) {
        log.info("根据类别查询商品");
        Page<Goods> page = new Page<>(current,size);
        LambdaQueryWrapper<Goods> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(Goods::getGoodSort, goodSort).like(Goods::getGoodName,goodName);
        return new ResultVo().setData(iGoodService.findByPage(page,userLambdaQueryWrapper));
    }
    @ApiOperation("根据id查询商品的详细信息")
    @GetMapping("/getGoodById")
    public ResultVo list(@ApiParam("商品id") int goodId) {
        log.info("根据id查询商品的详细信息");
        return new ResultVo().setData(iGoodService.queryById(goodId));
    }
    @RequiresRoles("user::user")
    @ApiOperation("商品基础信息修改")
    @GetMapping("/baseMessageUpdate")
    public ResultVo baseUpdate(@ApiParam("商品名称")String goodName,@ApiParam("概述") String summary,@ApiParam("详细介绍") String detail, @ApiParam("价格")double price, @ApiParam("分类")String goodSort,@ApiParam("商品id")int goodId) {
        log.info("商品基础信息修改");
        return new ResultVo().setData(iGoodService.update(goodName, summary, detail, price, goodSort,goodId));
    }
    @RequiresRoles("admin")
    @ApiOperation("查询所有未审核商品")
    @GetMapping("/select")
    public ResultVo select(@ApiParam("当前页")int current,@ApiParam("大小")int size) {
        log.info("查询所有未审核商品");
        Page<Goods> page = new Page<>(current , size );
        LambdaQueryWrapper<Goods> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(Goods::getApproved, "未审核");
        return new ResultVo().setData(iGoodService.findByPage(page,userLambdaQueryWrapper));
    }
    @RequiresRoles("admin")
    @ApiOperation("商品审核状态修改接口")
    @GetMapping("/approved")
    public ResultVo approved(String approved,int goodId) {
        log.info("商品审核状态修改接口");
        return new ResultVo().setData(iGoodService.approved(approved,goodId));
    }
    @RequiresRoles("user::user")
    @ApiOperation("商品图片修改接口")
    @PostMapping("/photoUpdate")
    public ResultVo photoUpdate(@ApiParam("图片")MultipartFile file, @ApiParam("商品id")int goodId) {
        log.info("商品图片修改接口");
        return new ResultVo().setData(iGoodService.updatePhoto(file, goodId));
    }
    @RequiresRoles("user::user")
    @ApiOperation("增加商品接口")
    @GetMapping("/insert")
    public ResultVo insert(@ApiParam("商品名称")String goodName, @ApiParam("概述")String summary, @ApiParam("详细介绍")String detail, @ApiParam("价格")double price, @ApiParam("分类")String goodSort,@ApiParam("用户id")int userId){
        log.info("增加商品接口");
        return new ResultVo().setData(iGoodService.insert(goodName,summary,detail,price,goodSort,userId));

    }
    @RequiresRoles("user::user")
    @ApiOperation("删除商品接口")
    @GetMapping("/remove")
    public ResultVo remove(@ApiParam("商品id")int goodId) {
        log.info("删除商品接口");
        return new ResultVo().setData(iGoodService.remove(goodId));
    }
    @ApiOperation("用户未出售商品查询接口")
    @GetMapping("/unsoldGood")
    public ResultVo unsoldGood(@ApiParam("用户id")int userId,@ApiParam("当前页")int current,@ApiParam("大小")int size) {
        log.info("用户未出售商品查询接口");
        Page<Goods> page = new Page<>(current,size);
        Subject subject = SecurityUtils.getSubject();
        User user =(User) subject.getPrincipal();
        LambdaQueryWrapper<Goods> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        if(user.getUserId()==userId){
            userLambdaQueryWrapper.eq(Goods::getUserId, userId).eq(Goods::getIsSold,false);
        }
        else{
            userLambdaQueryWrapper.eq(Goods::getUserId, userId).eq(Goods::getIsSold,false).eq(Goods::getApproved,"已审核");
        }
        return new ResultVo().setData(iGoodService.findByPage(page,userLambdaQueryWrapper));
    }
    @ApiOperation("用户已出售商品查询接口")
    @GetMapping("/isSoldGood")
    public ResultVo isSoldGood(@ApiParam("用户id")int userId,@ApiParam("当前页")int current,@ApiParam("大小")int size) {
        log.info("用户已出售商品查询接口");
        Page<Goods> page = new Page<>(current,size);
        Subject subject = SecurityUtils.getSubject();
        User user =(User) subject.getPrincipal();
        LambdaQueryWrapper<Goods> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        if(user.getUserId()==userId){
            userLambdaQueryWrapper.eq(Goods::getUserId, userId).eq(Goods::getIsSold,true);
        }
        else{
            userLambdaQueryWrapper.eq(Goods::getUserId, userId).eq(Goods::getIsSold,true).eq(Goods::getApproved,"已审核");
        }
        return new ResultVo().setData(iGoodService.findByPage(page,userLambdaQueryWrapper));
    }
    @RequiresRoles("user::user")
    @ApiOperation("商品出售情况修改接口")
    @GetMapping("/statusUpdate")
    public ResultVo statusUpdate(int goodId) {
        log.info("商品出售情况修改接口");
        return new ResultVo().setData(iGoodService.isSoldUpdate(goodId));
    }
}
