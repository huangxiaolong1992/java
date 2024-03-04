package com.example.myblog.service.impl;

import com.example.myblog.entity.Article;
import com.example.myblog.mapper.ArticleMapper;
import com.example.myblog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public int CreateArticle(Article article){
        return articleMapper.CreateArticle(article);
    }

    @Override
    public HashMap<Object, Object> listArticle(int page, int limit) {
        int size = (page - 1) * limit;
        int countArticle = articleMapper.countArticle();

        HashMap<Object, Object> hashMap = new HashMap<>();
        List<Article> artList = articleMapper.listArticle(size, limit);
        hashMap.put("list", artList);
        hashMap.put("page", page);
        hashMap.put("limit", limit);
        hashMap.put("total", countArticle);

        return hashMap;
    }

    @Override
    public Article ArticleDetail(int id) {
        return articleMapper.articleDetail(id);
    }

    @Override
    public int DeleteArticle(int id){
        return articleMapper.deleteArticle(id);
    }

    @Override
    public int UpdateArticle(Article article){
        return articleMapper.updateArticle(article);
    }
}
