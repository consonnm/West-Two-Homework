package com.example.fleamarket.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@TableName("reply")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("回复")
public class Reply{
    @TableId
    @ApiModelProperty("主键id")
    int Id;
    @ApiModelProperty("对应帖子id")
    int postId;
    @ApiModelProperty("回复用户的id")
    int sellManId;
    @ApiModelProperty("描述")
    @Length(max = 255, message = "输入的内容超过规定长度！")
    String description;
    @ApiModelProperty("图片")
    String pic;
    @ApiModelProperty("楼层")
    int floor;

}
