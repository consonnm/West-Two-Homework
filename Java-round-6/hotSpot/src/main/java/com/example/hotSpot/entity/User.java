package com.example.hotSpot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@TableName("user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户")
public class User{
	@TableId(type = IdType.AUTO)
	@ApiModelProperty("主键，自增")
	int userId;
	@ApiModelProperty("角色，ADMIN为管理员，VIP1为普通会员")
	String role;
	@ApiModelProperty("用户名")
	@Min(value = 18, message = "未成年不满足注册要求")
	@Max(value = 80, message = "年龄错误")
	String username;
	@ApiModelProperty("密码")
	String password;
	@ApiModelProperty("昵称")
	String nickname;
	@ApiModelProperty("盐值")
	String salt;
	@ApiModelProperty("头像")
	String image;
	@ApiModelProperty("年龄")
	String age;
	@ApiModelProperty("qq")
	String qq;
	@ApiModelProperty("电话")
	String phone;


}
