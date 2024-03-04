package com.example.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myblog.entity.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    @Insert("insert INTO Article(author,articleTitle, createTime, articleContent,coverImg,articleType, watchNum) values(#{author}, #{articleTitle}, #{createTime},#{articleContent},#{coverImg},#{articleType},#{watchNum})")
    int CreateArticle(Article article);

    @Select("select * from Article limit #{limit} offset #{size}")
    List<Article> listArticle(@Param("size") int size, @Param("limit") int limit);

    @Select("select count(*) from Article")
    int countArticle();

    @Select("select * from Article where id = #{id}")
    Article articleDetail(int id);

    @Update("update Article set deleteStatus=true where id = #{id}")
    int deleteArticle(int id);

    @Update("update Article set articleTitle=#{articleTitle}, articleContent=#{articleContent}, coverImg=#{coverImg} where id = #{id}")
    int updateArticle(Article article);
}
