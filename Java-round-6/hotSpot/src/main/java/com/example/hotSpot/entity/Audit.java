package com.example.hotSpot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@TableName("audit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Audit{
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("审核记录id")
    int auditId;
    @ApiModelProperty("id")
    int id;
    @ApiModelProperty("管理员id")
    int adminId;
    @ApiModelProperty("审核情况")
    Boolean status;
    @ApiModelProperty("审核的类型：0为热点,1为庭审")
    int sort;
}
