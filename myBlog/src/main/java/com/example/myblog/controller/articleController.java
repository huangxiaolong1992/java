package com.example.myblog.controller;

import com.example.myblog.common.R;
import com.example.myblog.common.ThreadLocalRequest;
import com.example.myblog.entity.Article;
import com.example.myblog.service.ArticleService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/article")
public class articleController {

    @Autowired
    private  ArticleService articleService;

    @PostMapping("/createArticle")
    @ApiOperation("创建文章")
    public R<String> createArticle(@RequestBody Article article) {
        String loginName =  ThreadLocalRequest.getInfo();
        article.setAuthor(loginName);
        articleService.CreateArticle(article);
        return R.success("创建成功", null);
    }

    @GetMapping("/listArticle")
    @ApiOperation("文章列表")
    private R<Object> listArticle(@RequestParam(value = "page", defaultValue = "1") int page,
                                  @RequestParam(value = "limit", defaultValue = "10") int limit
    ){

        Object listArticle = articleService.listArticle(page, limit);
        return R.success("查询成功", listArticle);
    }

    @GetMapping("/articleDetail")
    @ApiOperation("文章详情")
    private R<Article> articleDetail(int id){
       Article articleDetail =  articleService.ArticleDetail(id);
       return R.success("查询成功", articleDetail);
    }

    @DeleteMapping("/deleteArticle")
    @ApiOperation("删除文章")
    private R<String> deleteArticle(int id){
        int result = articleService.DeleteArticle(id);
        if(result == 1) {
            return R.success("删除成功", null);
        }else{
            return R.error("删除失败, id不存在");
        }
    }

    @PutMapping("/updateArticle")
    @ApiOperation("更新文章")
    private R<String> updateArticle(@RequestBody Article article){
        int result = articleService.UpdateArticle(article);
        if(result == 1) {
            return R.success("更新成功", null);
        }else{
            return R.error("更新失败");
        }
    }
}
