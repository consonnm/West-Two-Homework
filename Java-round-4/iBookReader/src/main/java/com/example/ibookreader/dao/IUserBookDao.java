package com.example.ibookreader.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ibookreader.entity.UserBook;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserBookDao extends BaseMapper<UserBook> {
    UserBook userBook(@Param("userName")String userName);
}