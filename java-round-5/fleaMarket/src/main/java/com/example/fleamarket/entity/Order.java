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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@TableName("orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "订单")
public class Order{
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("商品id")
    int goodId;
    @ApiModelProperty("出售用户的id")
    int  solderId;
    @ApiModelProperty("购买用户的id")
    int buyerId;
    @ApiModelProperty("订单的id")
    int orderId;
    @ApiModelProperty("下单时间")
    String time;
    @ApiModelProperty("购买人姓名")
    String buyerName;
    @ApiModelProperty("联系电话")
    String place;
    @ApiModelProperty("联系电话")
    String pay;
    @ApiModelProperty("订单状态")
    String orderStatus;

}
