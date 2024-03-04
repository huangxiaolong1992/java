package com.example.myblog.service;

import com.example.myblog.entity.Article;

import java.util.HashMap;

public interface ArticleService {
    int CreateArticle(Article article);

    HashMap<Object, Object> listArticle(int page, int limit);

    Article ArticleDetail(int id);

    int DeleteArticle(int id);

    int UpdateArticle(Article article);
}
