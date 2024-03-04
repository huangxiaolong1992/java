package com.example.myblog.mapper;

import com.example.myblog.entity.ArticleTypes;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleTypesMapper {
    @Insert("insert INTO articleTypes(name,coverImg, remark) values(#{name}, #{coverImg}, #{remark})")
    int CreateArticleType(ArticleTypes articleTypes);

    @Select("select * from articleTypes")
    List<ArticleTypes> ListArticleType();

    @Update("update articleTypes set name=#{name},coverImg=#{coverImg},remark=#{remark} where id = #{id}")
    int UpdateArticleType(ArticleTypes articleTypes);
}
