package com.example.ibookreader.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("user_book")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户收藏列表类")
public class UserBook {
    @ApiModelProperty("小说id")
    private int bookId;
    @ApiModelProperty("小说名")
    private String bookName;
    @ApiModelProperty("用户名")
    private String userName;
}
