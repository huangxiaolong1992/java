package com.example.myblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ArticleTypes {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String name;
    private String coverImg;
    private String remark;
}
