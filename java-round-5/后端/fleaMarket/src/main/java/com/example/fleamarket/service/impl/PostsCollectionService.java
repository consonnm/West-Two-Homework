package com.example.fleamarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.fleamarket.dao.IGoodsCollectionDao;
import com.example.fleamarket.dao.IPostsCollectionDao;
import com.example.fleamarket.entity.GoodsCollection;
import com.example.fleamarket.entity.PostsCollection;
import com.example.fleamarket.service.IGoodsCollectionService;
import com.example.fleamarket.service.IPostsCollectionService;
import org.springframework.stereotype.Service;

@Service
public class PostsCollectionService extends ServiceImpl<IPostsCollectionDao, PostsCollection> implements IPostsCollectionService {
    public Boolean insert(int userId,int goodId){
        PostsCollection postsCollection = new PostsCollection();
        postsCollection.setUserId(userId);
        postsCollection.setPostId(goodId);
        return save(postsCollection);
    }
    public Boolean remove(int userId,int postId) {
        LambdaQueryWrapper<PostsCollection> lwq = Wrappers.lambdaQuery();
        lwq.eq(PostsCollection::getUserId,userId).eq(PostsCollection::getPostId,postId);
        return remove(lwq);
    }
    public IPage<PostsCollection> findByPage(Page<PostsCollection> page, LambdaQueryWrapper<PostsCollection> userLambdaQueryWrapper){
        return  baseMapper.selectPage(page,userLambdaQueryWrapper);
    }

}
