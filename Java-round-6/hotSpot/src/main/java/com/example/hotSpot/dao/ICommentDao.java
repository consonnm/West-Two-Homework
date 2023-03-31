package com.example.hotSpot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hotSpot.entity.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentDao extends BaseMapper<Comment> {

}
