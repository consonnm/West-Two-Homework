package com.example.fleamarket.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@TableName("goodReport")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodReport {
    @TableId
    @ApiModelProperty("审核记录id")
    int reportId;
    @ApiModelProperty("商品的id")
    int goodId;
    @ApiModelProperty("举报理由")
    @Length(max = 255, message = "输入的内容超过规定长度！")
    String context;
    @ApiModelProperty("审核情况")
    String status;
}
