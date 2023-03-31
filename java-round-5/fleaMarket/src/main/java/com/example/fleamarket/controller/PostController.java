package com.example.fleamarket.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fleamarket.entity.Category;
import com.example.fleamarket.entity.Goods;
import com.example.fleamarket.entity.Posts;
import com.example.fleamarket.exception.ControllerException;
import com.example.fleamarket.response.ResultVo;
import com.example.fleamarket.service.ICategoryService;
import com.example.fleamarket.service.IPostsService;
import com.example.fleamarket.service.IReplyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/post")
public class PostController {
    @Autowired
    IPostsService iPostsService;
    IReplyService iReplyService;
    ICategoryService iCategoryService;

    @ApiOperation("帖子标签添加接口")
    @GetMapping("/AddPostCategory")
    public ResultVo AddPostCategory(@ApiParam("帖子id") int postId, List<Category> categoryList) {
        log.info("帖子标签添加接口");

        iPostsService.addCategory(postId, categoryList);

        return new ResultVo().setCode(200);

    }
    @ApiOperation("帖子模糊查询接口")
    @GetMapping("/LikelyQueryPost")
    public ResultVo list(@ApiParam("寻物描述") String postContent,@ApiParam("当前页")int current,@ApiParam("大小")int size) {
        log.info("模糊查询帖子"+postContent);
        Page<Posts> page = new Page<>(current, size);
        LambdaQueryWrapper<Posts> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.like(Posts::getDescription, postContent);
        return new ResultVo().setData(iPostsService.findByPage(page, userLambdaQueryWrapper));
    }
    @ApiOperation("帖子标签查询接口")
    @GetMapping("/getPostsCategory")
    public ResultVo queryPostCategory(@ApiParam("帖子id") int postId) {
        log.info("帖子标签查询接口");
        Posts P = iPostsService.queryById(postId);
        P.setCategoryList(
                iPostsService.findCategorylist(postId)
        );
        return new ResultVo().setData(P);

    }

    @ApiOperation("帖子内容查询接口")
    @GetMapping("/getPostsContent")
    public ResultVo queryPostContent(@ApiParam("帖子id") int postId) {
        log.info("帖子内容查询接口");
        Posts P = iPostsService.queryById(postId);
        if(P==null){
            throw new ControllerException("不存在该帖子");
        }
        P.setReplyList(
                iReplyService.queryForReplyList(postId)
        );
        return new ResultVo().setData(P);

    }

    @ApiOperation("增加帖子接口")
    @GetMapping("/insert")
    public ResultVo insert( @ApiParam("购买者id") int buyerId, @ApiParam("描述") String description) {
        log.info("增加帖子接口");

        return new ResultVo().setData(iPostsService.insert( buyerId, description));
    }

    @ApiOperation("用户拥有帖子查询接口")
    @GetMapping("/getUserPost")
    public ResultVo query(@ApiParam("帖子id") int buyerId) {
        log.info("用户拥有帖子查询接口");
        List<Posts> posts;

        posts = iPostsService.list(
                new LambdaQueryWrapper<Posts>()
                        .eq(Posts::getBuyerId, buyerId));
        return new ResultVo().setData(posts);

    }

    @ApiOperation("根据标签查询帖子接口")
    @GetMapping("/getPostsByCategory")
    public ResultVo queryPostByCategory(@ApiParam("标签名字") String categoryName) {
        log.info("根据标签查询帖子接口");
               Category c= iCategoryService.getOne(new LambdaQueryWrapper<Category>()
                       .eq(Category::getGoodSort, categoryName));
               if (c==null){
                   throw new ControllerException("该标签不存在");
               }
               List<Posts>list= iCategoryService.findPostsByCategory(c.getCategoryId());

        return new ResultVo().setData(list);

    }

    @ApiOperation("删除帖子接口")
    @GetMapping("/deletePosts")
    public ResultVo deletePosts(@ApiParam("帖子id") int postId) {
        log.info("删除帖子接口");

        return new ResultVo().setData(iPostsService.remove(postId));

    }

}
