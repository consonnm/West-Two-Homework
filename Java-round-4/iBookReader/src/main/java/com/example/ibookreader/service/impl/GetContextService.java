package com.example.ibookreader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ibookreader.dao.IGetBookContextDao;
import com.example.ibookreader.entity.BookContext;
import com.example.ibookreader.service.IGetContextService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetContextService extends ServiceImpl<IGetBookContextDao, BookContext> implements IGetContextService {
    @Override
    public BookContext queryById(String bookName,String bookChapter) {
        return getOne(new LambdaQueryWrapper<BookContext>()
                .eq(BookContext::getBookChapter,bookChapter)
                .eq(BookContext::getBookName,bookName)
                .select(BookContext::getBookContext)
        );
    }
    @Override
    public Boolean insertBook(String bookName,String bookChapter,String bookContext) {
        BookContext book = new BookContext();
        book.setBookName(bookName);
        book.setBookChapter(bookChapter);
        book.setBookContext(bookContext);
        return save(book);
    }

}
