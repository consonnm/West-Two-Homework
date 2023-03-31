package com.example.ibookreader.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ibookreader.entity.BookContext;

public interface IGetContextService extends IService<BookContext> {

    BookContext queryById(String bookName,String bookChapter);
    Boolean insertBook(String bookName,String bookChapter,String bookContext);

}
