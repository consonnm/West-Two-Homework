package com.example.fleamarket.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fleamarket.entity.Category;
import com.example.fleamarket.entity.Posts;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryDao extends BaseMapper<Category> {

    @Select("select * from post " +
            " inner join " +
            " post_category on post.post_id=post_category.post_id " +
            " where  post_category.category_id =#{categoryId} ")
    @Results({
            @Result(id = true, column = "post_id", property = "id"),
    })
    List<Posts> getPostsByCategoryId(int categoryId);
}
