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

import javax.validation.constraints.Pattern;


@TableName("judge")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("热点类")
public class Judge {
	@TableId(type = IdType.AUTO)
	@ApiModelProperty("庭审id")
	int judgeId;
	@ApiModelProperty("用户的id")
	int  userId;
	@ApiModelProperty("热点的id")
	int spotId;
	@ApiModelProperty("庭审名称")
	@Length(max = 255, message = "输入的内容超过规定长度！")
	String judgeName;
	@ApiModelProperty("庭审简介")
	@Length(max = 255, message = "输入的内容超过规定长度！")
	String summary;
	@ApiModelProperty("审核情况")
	@Pattern(regexp = "(已审核)|(未审核)|(审核未通过)")
	String approved;
	@ApiModelProperty("庭审详情介绍")
	@Length(max = 255, message = "输入的内容超过规定长度！")
	String detail;
	@ApiModelProperty("图片")
	String image;
	@ApiModelProperty("点击数")
	int clickAmount;
	@ApiModelProperty("热点发布时间")
	String time;
	@ApiModelProperty("乘数，用于推荐排序，默认值为10000")
	int multiplier;
	@ApiModelProperty("加数，用于推荐排序，默认值为0。排序值为：推荐数*乘数+加数")
	int addend;
	@ApiModelProperty("正方票数")
	int positive;
	@ApiModelProperty("反方票数")
	int negative;
}
