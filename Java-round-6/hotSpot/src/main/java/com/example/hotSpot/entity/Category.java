package com.example.hotSpot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@TableName("category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "热点分类")
public class Category{
	@TableId(type = IdType.AUTO)
	@ApiModelProperty("主键，自增")
	int CategoryId;
	@ApiModelProperty("名称")
	String spotSort;
	@ApiModelProperty("描述")
	String spotDescribe;

}
