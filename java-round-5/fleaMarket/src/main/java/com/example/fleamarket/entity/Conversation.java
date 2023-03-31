package com.example.fleamarket.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@TableName("posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("帖子")
public class Conversation {

    @ApiModelProperty("用户1id")
    int User1Id;
    @ApiModelProperty("用户2id")
    int User2Id;
    @ApiModelProperty("回复列表")
    @TableField(exist = false)
    List<Message> messages;


}
