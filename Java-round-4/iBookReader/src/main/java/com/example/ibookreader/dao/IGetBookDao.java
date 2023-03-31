package com.example.ibookreader.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ibookreader.entity.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IGetBookDao extends BaseMapper <Book> {
    Book book(@Param("bookChapter") String bookChapter, @Param("bookName") String bookName);
}
