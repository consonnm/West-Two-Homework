package com.example.ibookreader.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("book_context")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("小说章节类")
public class BookContext {
    @TableId
    @ApiModelProperty("小说章节名")
    private String bookChapter;
    @ApiModelProperty("小说名字")
    private String bookName;
    @ApiModelProperty("小说id")
    private int bookId;
    @ApiModelProperty("小说内容")
    private String bookContext;
}
