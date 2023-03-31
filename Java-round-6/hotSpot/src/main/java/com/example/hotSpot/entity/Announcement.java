package com.example.hotSpot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@TableName("announcement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Announcement{
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("公告id")
    int announcementId;
    @ApiModelProperty("公告主题")
    String topic;
    @ApiModelProperty("公告内容")
    @Length(max = 255, message = "输入的内容超过规定长度！")
    String context;
    @ApiModelProperty("公告时间")
    String time;

}
