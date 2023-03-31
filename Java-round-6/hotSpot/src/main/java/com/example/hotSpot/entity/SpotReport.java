package com.example.hotSpot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@TableName("spotReport")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpotReport {
    @TableId
    @ApiModelProperty("审核记录id")
    int reportId;
    @ApiModelProperty("热点的id")
    int spotId;
    @ApiModelProperty("举报理由")
    @Length(max = 255, message = "输入的内容超过规定长度！")
    String context;
    @ApiModelProperty("审核情况")
    String status;
}
