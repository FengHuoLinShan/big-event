package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mapper.ArticleMapper;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.User;
import com.itheima.service.ArticleService;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);

        // 调用Mapper
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = userMapper.findByUserName((String) map.get("username")).getId();
        List<Article> as = articleMapper.list(userId, categoryId, state);

        // 使用 PageInfo 处理分页数据
        PageInfo<Article> pageInfo = new PageInfo<>(as);

        // 封装 PageBean
        PageBean<Article> pb = new PageBean<>();
        pb.setTotal(pageInfo.getTotal());
        pb.setItems(pageInfo.getList());

        return pb;
    }


    @Override
    public void add(Article article) {
        //初始赋值创建时间等
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        article.setCategoryId(article.getCategoryId());
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        Integer id = userMapper.findByUserName(username).getId();
        article.setCreateUser(id);

        articleMapper.add(article);
    }

}
