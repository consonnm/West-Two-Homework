package com.example.ibookreader.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.ibookreader.entity.UserBook;
import com.example.ibookreader.service.IUserBookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value ="/userBook")
public class UerCollectionController {
    @Autowired
    private IUserBookService iUserBookService;

    @GetMapping("/queryId")
    public UserBook queryById(@ApiParam("用户名") String userName){
        return iUserBookService.queryById(userName);
    }

    @ApiOperation("查询书架接口")
    @GetMapping("/list")
    public List<UserBook> list(@ApiParam("用户名") String userName){
        return iUserBookService.list(new LambdaQueryWrapper<UserBook>()
                .eq(UserBook::getUserName,userName)
                .select(UserBook::getBookName)
        );
    }

    @ApiOperation("加入书架接口")
    @GetMapping("/insert")
    public Boolean insertBook(@ApiParam("用户名") String userName,@ApiParam("书籍名") String bookName){
        return iUserBookService.insertBook(userName,bookName);
    }
    @ApiOperation("删除收藏接口")
    @GetMapping("/remove")
    public Boolean remove(@ApiParam("用户名")String userName,@ApiParam("书籍名")String bookName){
        return iUserBookService.remove(userName,bookName);
    }


}
