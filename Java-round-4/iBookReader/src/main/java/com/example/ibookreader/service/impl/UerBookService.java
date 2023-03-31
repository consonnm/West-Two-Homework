package com.example.ibookreader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ibookreader.dao.IUserBookDao;
import com.example.ibookreader.entity.UserBook;
import com.example.ibookreader.service.IUserBookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UerBookService extends ServiceImpl<IUserBookDao, UserBook> implements IUserBookService {
    private IUserBookDao iUserBookDao;

    @Override
    public UserBook queryById(String userName) {
        return getOne(new LambdaQueryWrapper<UserBook>()
                .eq(UserBook::getUserName,userName)
        );
    }

    @Override
    public Boolean insertBook(String userName, String bookName) {
        UserBook userBook = new UserBook();
        userBook.setUserName(userName);
        userBook.setBookName(bookName);
        return save(userBook);
    }

    @Override
    public Boolean remove(String userName, String bookName) {
        LambdaQueryWrapper<UserBook> lwq = Wrappers.lambdaQuery();
        lwq.eq(UserBook::getUserName,userName).eq(UserBook::getBookName,bookName);
        return remove(lwq);
    }
}
