package com.example.myblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


@Data
public class Article {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String author;
    private String articleTitle;
    private String createTime;
    private String articleContent;
    private String coverImg;
    private String articleType;
    private int watchNum;
    private Boolean deleteStatus;
}
