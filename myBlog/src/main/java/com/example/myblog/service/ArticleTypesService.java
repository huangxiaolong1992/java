package com.example.myblog.service;

import com.example.myblog.entity.ArticleTypes;

import java.util.List;

public interface ArticleTypesService {
    int CreateArticleType(ArticleTypes articleTypes);

    List<ArticleTypes> ListArticleType();

    int UpdateArticleType(ArticleTypes articleTypes);
}
