package com.example.fleamarket.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fleamarket.entity.Category;
import com.example.fleamarket.entity.Posts;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostsDao extends BaseMapper<Posts> {
    @Select("select * from category " +
            " inner join " +
            " post_category on category.id=post_category.category_id " +
            " where  post_category.post_id =#{PostId} ")
    @Results({
            @Result(id = true, column = "id", property = "CategoryId"),
            @Result(column = "name", property = "goodSort"),
    })
    List<Category> getCategoryByPostId(int PostId);


    @Insert("insert into post_category(id,post_id,category_id) values(null,#{PostId},#{category_id})")
    boolean addCategory(int PostId,int category_id);

}


