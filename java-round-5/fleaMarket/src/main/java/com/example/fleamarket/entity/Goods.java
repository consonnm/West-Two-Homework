package com.example.fleamarket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Pattern;


@TableName("goods")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("商品类")
public class Goods{
	@TableId(type = IdType.AUTO)
	@ApiModelProperty("商品id")
	int goodId;
	@ApiModelProperty("用户的id")
	int  userId;
	@ApiModelProperty("商品价格")
	double price;
	@ApiModelProperty("分类的id")
	int categoryId;
	@ApiModelProperty("名称")
	@Length(max = 255, message = "输入的内容超过规定长度！")
	String goodName;
	@ApiModelProperty("简介")
	@Length(max = 255, message = "输入的内容超过规定长度！")
	String summary;
	@ApiModelProperty("审核情况")
	@Pattern(regexp = "(已审核)|(未审核)|(审核未通过)")
	String approved;
	@ApiModelProperty("详情介绍")
	@Length(max = 255, message = "输入的内容超过规定长度！")
	String detail;
	@ApiModelProperty("图片")
	String image;
	@ApiModelProperty("出售情况")
	Boolean isSold;
	@ApiModelProperty("点击数")
	int clickAmount;
	@ApiModelProperty("上架时间")
	String time;
	@ApiModelProperty("商品的类别")
	String goodSort;
	@ApiModelProperty("乘数，用于推荐排序，默认值为10000")
	int multiplier;
	@ApiModelProperty("加数，用于推荐排序，默认值为0。排序值为：推荐数*乘数+加数")
	int addend;
}
