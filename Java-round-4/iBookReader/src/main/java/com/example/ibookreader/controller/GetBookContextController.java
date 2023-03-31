package com.example.ibookreader.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.ibookreader.entity.BookContext;
import com.example.ibookreader.service.IGetContextService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value ="/getContext")
public class GetBookContextController {
    @Autowired
    private IGetContextService iGetContextService;
    @ApiOperation("获取小说列表接口")
    @GetMapping("/getChapter")
    public List<BookContext> list(@ApiParam("书籍名") String bookName){
        return iGetContextService.list(new LambdaQueryWrapper<BookContext>()
                .eq(BookContext::getBookName,bookName)
                .select(BookContext::getBookChapter)
        );
    }
    @ApiOperation("下载小说")
    @GetMapping("/getAll")
    public List<BookContext> listBook(@ApiParam("书籍名") String bookName){
        return iGetContextService.list(new LambdaQueryWrapper<BookContext>()
                .eq(BookContext::getBookName,bookName)
                .select(BookContext::getBookContext,
                        BookContext::getBookChapter)

        );
    }

    @ApiOperation("获取章节内容接口")
    @GetMapping("/getText")
    public BookContext queryById(@ApiParam("小说名") String bookName,@ApiParam("章节名") String bookChapter){
        return iGetContextService.queryById(bookName,bookChapter);
    }



}
