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

@TableName("spot")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("热点类")
public class Spot {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("热点id")
    int spotId;
    @ApiModelProperty("用户的id")
    int  userId;
    @ApiModelProperty("分类的id")
    int categoryId;
    @ApiModelProperty("热点名称")
    @Length(max = 255, message = "输入的内容超过规定长度！")
    String spotName;
    @ApiModelProperty("热点简介")
    @Length(max = 255, message = "输入的内容超过规定长度！")
    String summary;
    @ApiModelProperty("审核情况")
    @Pattern(regexp = "(已审核)|(未审核)|(审核未通过)")
    String approved;
    @ApiModelProperty("热点详情介绍")
    @Length(max = 255, message = "输入的内容超过规定长度！")
    String detail;
    @ApiModelProperty("图片")
    String image;
    @ApiModelProperty("点击数")
    int clickAmount;
    @ApiModelProperty("热点发布时间")
    long time;
    @ApiModelProperty("热点的类别")
    String spotSort;
    @ApiModelProperty("乘数，用于推荐排序，默认值为10000")
    int multiplier;
    @ApiModelProperty("加数，用于推荐排序，默认值为0。排序值为：推荐数*乘数+加数")
    int addend;
}

