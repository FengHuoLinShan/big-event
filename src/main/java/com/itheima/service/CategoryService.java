package com.itheima.service;

import com.itheima.pojo.Category;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;

import java.util.List;

public interface CategoryService {
    //新增分类
    void add(Category category);

    //根据用户名查询用户
    User findByUserName(String username);

    //返回文章列表
    List<Category> list();

    //根据category主键id返回该category
    Category findById(Integer id);

    //更新文章分类
    void update(Category category);

    //删除文章分类
    void delete(Integer id);
}
