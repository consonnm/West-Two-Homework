package com.example.fleamarket.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@TableName("history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "浏览记录")
public class History{
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("主键，自增")
    int id;
    @ApiModelProperty("用户的id")
    int userId;
    @ApiModelProperty("商品的id")
    int goodsId;
}
