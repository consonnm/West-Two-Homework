package com.example.fleamarket.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fleamarket.entity.Order;
import com.example.fleamarket.response.ResultVo;
import com.example.fleamarket.service.IOrderService;
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
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    IOrderService iOrderService;
    @RequiresRoles("user::user")
    @ApiOperation("用户购买信息查询接口")
    @GetMapping("/buy")
    public ResultVo buy(@ApiParam("购买者id")int buyerId,@ApiParam("当前页")int current,@ApiParam("大小") int size) {
        log.info("用户购买信息查询接口");
        Page<Order> page = new Page<>(current,size);
        LambdaQueryWrapper<Order> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(Order::getBuyerId,buyerId);
        return new ResultVo().setData(iOrderService.findByPage(page,userLambdaQueryWrapper));
    }
    @RequiresRoles("user::user")
    @ApiOperation("用户出售信息查询接口")
    @GetMapping("/sold")
    public ResultVo sold(@ApiParam("订单id")int orderId,@ApiParam("当前页")int current,@ApiParam("大小") int size) {
        log.info("用户出售信息查询接口");
        Page<Order> page = new Page<>(current,size);
        LambdaQueryWrapper<Order> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(Order::getSolderId, orderId);
        return new ResultVo().setData(iOrderService.findByPage(page,userLambdaQueryWrapper));
    }
    @RequiresRoles("user::user")
    @ApiOperation("用户取消订单接口")
    @GetMapping("/cancel")
    public ResultVo cancel(@ApiParam("订单id")int orderId) {
        log.info("用户取消订单接口");
        return new ResultVo().setData(iOrderService.statusUpdate(orderId));
    }
    @RequiresRoles("user::user")
    @ApiOperation("用户修改订单接口")
    @GetMapping("/baseUpdate")
    public ResultVo baseUpdate(@ApiParam("购买者姓名")String buyerName, @ApiParam("地点")String place,@ApiParam("支付方式")String pay,@ApiParam("订单id")int orderId) {
        log.info("用户修改订单接口");
        return new ResultVo().setData(iOrderService.baseUpdate(buyerName, place,pay,orderId));
    }

    @ApiOperation("用户收货接口")
    @GetMapping("/update")
    public ResultVo statusUpdate(@ApiParam("订单id")int orderId) {
        log.info("用户收货接口");
        return new ResultVo().setData(iOrderService.statusUpdate(orderId));
    }
    @ApiOperation("增加商品接口")
    @GetMapping("/insert")
    public ResultVo insert(@ApiParam("商品id")int goodId,@ApiParam("出售人id")int solderId,@ApiParam("购买者id")int buyerId,@ApiParam("时间")String time,@ApiParam("购买者姓名")String buyerName,@ApiParam("地点")String place,@ApiParam("支付方式")String pay) {
        return new ResultVo().setData(iOrderService.insert(goodId,solderId,buyerId,time,buyerName,place,pay));
    }

}
