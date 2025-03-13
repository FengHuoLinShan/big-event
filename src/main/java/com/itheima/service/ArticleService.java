package com.itheima.service;

import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.User;

import java.util.List;

public interface ArticleService {

    //添加文章
    void add(Article article);

    //根据用户名查询用户
    User findByUserName(String username);

    //分页查询文章
    List<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
