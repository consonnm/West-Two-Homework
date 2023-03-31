package com.example.ibookreader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ibookreader.dao.IGetBookDao;
import com.example.ibookreader.entity.Book;
import com.example.ibookreader.service.IGetBookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetBookService extends ServiceImpl <IGetBookDao, Book> implements IGetBookService{
    //private IGetBookDao iGetBookDao;

    @Override
    public Book queryById(String bookName) {
        return getOne(new LambdaQueryWrapper<Book>()
                .eq(Book::getBookName,bookName)
        );
    }

    @Override
    public Book photo(String bookName) {
        return getOne(new LambdaQueryWrapper<Book>()
                .eq(Book::getBookName,bookName)
                .select(Book::getBookPhoto)
        );
    }


    @Override
    public Boolean insertBook(String bookName,String bookAuthor,String bookIntroduction,String bookPhoto) {
        Book book = new Book();
        book.setBookName(bookName);
        book.setBookAuthor(bookAuthor);
        book.setBookIntroduction(bookIntroduction);
        book.setBookPhoto(bookPhoto);
        return save(book);
    }

}
