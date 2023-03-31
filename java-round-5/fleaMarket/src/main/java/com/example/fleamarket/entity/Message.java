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
	@ApiModelProperty("用户1的id")
	int User1Id;

	@ApiModelProperty("用户2的id")
	int User2Id;
	@ApiModelProperty("消息内容")
	@Length(max = 255, message = "输入的内容超过规定长度！")
	String content;
	@ApiModelProperty("发送时间")
	LocalDate date;
}
