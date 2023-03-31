package com.example.ibookreader.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ibookreader.entity.UserBook;

public interface IUserBookService extends IService<UserBook> {

    UserBook queryById(String userName);

    Boolean insertBook(String userName, String bookName);

    Boolean remove(String userName, String bookName);
}
