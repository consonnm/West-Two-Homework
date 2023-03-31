package com.example.hotSpot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@TableName("vote")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "投票")
public class Vote {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("主键，自增")
    int voteId;
    @ApiModelProperty("用户id")
    int userId;
    @ApiModelProperty("热点id")
    int spotId;
}
