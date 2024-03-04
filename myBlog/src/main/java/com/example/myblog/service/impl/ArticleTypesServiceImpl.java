package com.example.myblog.service.impl;

import com.example.myblog.entity.ArticleTypes;
import com.example.myblog.mapper.ArticleTypesMapper;
import com.example.myblog.service.ArticleTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleTypesServiceImpl implements ArticleTypesService {
    @Autowired
    private ArticleTypesMapper articleTypesMapper;
    @Override
    public int CreateArticleType(ArticleTypes articleTypes){
        return  articleTypesMapper.CreateArticleType(articleTypes);
    }

    @Override
    public List<ArticleTypes> ListArticleType(){
        return  articleTypesMapper.ListArticleType();
    }

    @Override
    public int UpdateArticleType(ArticleTypes articleTypes){
        return  articleTypesMapper.UpdateArticleType(articleTypes);
    }
}
