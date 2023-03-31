package com.example.ibookreader.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("book")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("书籍类")
public class Book {
    @TableId
    @ApiModelProperty("书名")
    private String bookName;
    @ApiModelProperty("小说介绍")
    private String bookIntroduction;
    @ApiModelProperty("小说id")
    private int bookId;
    @ApiModelProperty("小说作者")
    private String bookAuthor;
    @ApiModelProperty("小说照片")
    private String bookPhoto;
}
