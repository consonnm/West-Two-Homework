package com.example.hotSpot.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("time_line")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeLine {
    @ApiModelProperty("时间线id")
    int timeId;
    @ApiModelProperty("热点id")
    int spotId;
    @ApiModelProperty("时间")
    String timeNow;
    @ApiModelProperty("描述")
    String timeDescribe;
}
