package com.example.fleamarket.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fleamarket.entity.GoodsCollection;
import com.example.fleamarket.exception.ControllerException;
import com.example.fleamarket.response.ResultVo;
import com.example.fleamarket.service.IGoodsCollectionService;
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
@RequestMapping(value ="/postsCollection")
public class PostsCollectionController {
    @Autowired
    IGoodsCollectionService iPostsCollectionService;
    @RequiresRoles("user::user")
    @ApiOperation("查询所有收藏")
    @GetMapping("/getAllFollow")
    public ResultVo all(@ApiParam("用户id")int userId, @ApiParam("当前页")int current,@ApiParam("大小") int size){
        log.info("查询所有关注");
        Page<GoodsCollection> page = new Page<>(current , size );
        LambdaQueryWrapper<GoodsCollection> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(GoodsCollection::getUserId,userId);
        return new ResultVo().setData(iPostsCollectionService.findByPage(page,userLambdaQueryWrapper));
    }
    @RequiresRoles("user::user")
    @ApiOperation("增加收藏")
    @GetMapping("/insert")
    public ResultVo insert(@ApiParam("用户id")int userId,@ApiParam("帖子id")int goodId){
        log.info("增加收藏");
        return new ResultVo().setData(iPostsCollectionService.insert(userId,goodId));
    }
    @RequiresRoles("user::user")
    @ApiOperation("删除收藏")
    @GetMapping("/remove")
    public ResultVo remove(@ApiParam("用户id")int userId,@ApiParam("帖子id")int goodId){
        log.info("删除收藏");
        if(iPostsCollectionService.remove(userId,goodId)==true){
            return new ResultVo().setCode(200);
        }
       else throw new ControllerException("id不存在");
    }
}
