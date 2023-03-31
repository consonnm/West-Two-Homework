package com.example.ibookreader.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.ibookreader.entity.Book;
import com.example.ibookreader.service.IGetBookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value ="/getBook")
public class GetBookController {
    @Autowired
    private IGetBookService iGetBookService;

    @ApiOperation("获取书的简介等接口")
    @GetMapping("/queryById")
    public Book queryById(@ApiParam("书籍名") String bookName){
        return iGetBookService.queryById(bookName);
    }

    @ApiOperation("获取书籍封面查接口")
    @GetMapping("/photo")
    public Book photo(@ApiParam("书籍名") String bookName){
        return iGetBookService.photo(bookName);
    }

    @ApiOperation("搜索操作接口")
    @GetMapping("/search")
    public List<Book> list(@ApiParam("书籍名") String bookName){
        return iGetBookService.list(new LambdaQueryWrapper<Book>()
                .like(Book::getBookName,bookName)

        );

    }
    @ApiOperation("推荐书籍接口")
    @GetMapping("/show")
    public List<Book> list(){
        return iGetBookService.list(new LambdaQueryWrapper<Book>()
                        .le(Book::getBookId,8)
                );

    }


}
