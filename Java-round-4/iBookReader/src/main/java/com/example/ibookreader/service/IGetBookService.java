package com.example.ibookreader.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ibookreader.entity.Book;

public interface IGetBookService extends IService<Book> {
    Book queryById(String bookName);

    Book photo(String bookName);

    Boolean insertBook(String bookName,String bookAuthor,String bookIntroduction,String bookPhoto);
}
