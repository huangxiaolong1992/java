package com.example.myblog.controller;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.example.myblog.common.R;
import com.example.myblog.common.ThreadLocalRequest;
import com.example.myblog.entity.ArticleTypes;
import com.example.myblog.service.ArticleTypesService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController

public class articleTypesController {
    @Value("${token.key}")
    private String key;

    @Value("${manager.userName}")
    private String userAdmin;

    @Autowired
    private ArticleTypesService articleTypesService;

    @PostMapping("/createArticleType")
    @ApiOperation("创建文章分类")
    public R<String> createArticleType(HttpServletRequest request, @RequestBody ArticleTypes articleTypes) {
        String loginName = ThreadLocalRequest.getInfo();
        if(loginName.equals(userAdmin)){
            articleTypesService.CreateArticleType(articleTypes);
            return R.success("创建成功", null);
        }else{
            return R.error("无权限创建");
        }
    }

    @GetMapping ("/listArticleType")
    @ApiOperation("获取文章分类")
    public R<Object> listArticleType() {
        List<ArticleTypes> listArticleType = articleTypesService.ListArticleType();
        return R.success("查询成功", listArticleType);
    }

    @PutMapping("/updateArticleType")
    @ApiOperation("更新文章分类")
    public R<String> updateArticleType(@RequestBody ArticleTypes articleTypes) {
        int result = articleTypesService.UpdateArticleType(articleTypes);
        if(result == 1) {
            return R.success("更新成功", null);
        }else{
            return R.error("更新失败");
        }
    }
}
