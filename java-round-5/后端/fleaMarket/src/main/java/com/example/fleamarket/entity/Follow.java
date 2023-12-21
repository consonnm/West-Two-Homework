package com.example.fleamarket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@TableName("follow")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "关注")
public class Follow{
	@TableId(type = IdType.AUTO)
	@ApiModelProperty("主键，自增")
	Integer id;
	@ApiModelProperty("关注者的id")
	Integer followerId;
	@ApiModelProperty("被关注者的id")
	Integer followedId;
}
