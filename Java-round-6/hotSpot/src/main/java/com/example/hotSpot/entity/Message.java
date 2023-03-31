package com.example.hotSpot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;


@TableName("message")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "消息")
public class Message {
	@TableId
	@ApiModelProperty("消息id")
	int messageId;
	@ApiModelProperty("发送用户的id")
	int UserId;
	@ApiModelProperty("热点Id")
	int spotId;
	@ApiModelProperty("消息内容")
	@Length(max = 255, message = "输入的内容超过规定长度！")
	String content;
	@ApiModelProperty("发送时间")
	String date;
	@ApiModelProperty("消息的类型，0为图片，1为文本")
	int category;
	@ApiModelProperty("当前在线人数")
	int number;
}
