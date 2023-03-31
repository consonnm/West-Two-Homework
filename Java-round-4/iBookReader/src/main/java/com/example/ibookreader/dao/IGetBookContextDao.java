package com.example.ibookreader.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ibookreader.entity.BookContext;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IGetBookContextDao extends BaseMapper<BookContext> {
    BookContext bookcontext(@Param("bookName")String bookName);
}
