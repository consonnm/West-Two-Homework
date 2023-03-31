package com.example.hotSpot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@TableName("comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户评价")
public class Comment{
	@TableId(type = IdType.AUTO)
	@ApiModelProperty("主键，自增")
	int commentId;
	@ApiModelProperty("id")
	int id;
	@ApiModelProperty("评价用户的id")
	int commentedUserId;
	@ApiModelProperty("评论的内容")
	@Length(max = 255, message = "输入的内容超过规定长度！")
	String content;
	@ApiModelProperty("评论的类型：0为热点,1为庭审")
	int sort;
}
