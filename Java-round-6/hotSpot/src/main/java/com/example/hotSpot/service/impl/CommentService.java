package com.example.hotSpot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hotSpot.dao.ICommentDao;
import com.example.hotSpot.entity.Comment;
import com.example.hotSpot.service.ICommentService;
import org.springframework.stereotype.Service;


@Service
public class CommentService extends ServiceImpl<ICommentDao, Comment> implements ICommentService {
	@Override
	public Boolean insert(int commentId,int becommentedId,String Context,int sort){
		Comment comment = new Comment();
		comment.setId(commentId);
		comment.setSort(sort);
		comment.setCommentedUserId(becommentedId);
		comment.setContent(Context);
		return save(comment);
	}
	@Override
	public Boolean remove(int commentId) {
		LambdaQueryWrapper<Comment> lwq = Wrappers.lambdaQuery();
		lwq.eq(Comment::getCommentId,commentId);
		return remove(lwq);
	}
	@Override
	public IPage<Comment> findByPage(Page<Comment> page, LambdaQueryWrapper<Comment> userLambdaQueryWrapper){
		return  baseMapper.selectPage(page,userLambdaQueryWrapper);
	}

}
