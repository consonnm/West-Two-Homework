package com.example.fleamarket.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fleamarket.entity.GoodsCollection;
import com.example.fleamarket.entity.PostsCollection;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostsCollectionDao extends BaseMapper<PostsCollection> {

}
