package com.nnon.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nnon.server.mapper.ArticleMapper;
import com.nnon.server.pojo.Article;
import com.nnon.server.pojo.ArticleClassify;
import com.nnon.server.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    ArticleMapper mapper;
    @Override
    public int addArticle(Article article) {
        return mapper.addArticle(article);
    }

    @Override
    public PageInfo<Article> findArticlePage(int page, int size, Article article) {
        PageHelper.startPage(page,size);//分页起始码以及每页页数
        List<Article> articleList=null;
        if(article!=null&&article.getTag()!=null){
            //如果标签参数不为空 则会通过多表查询
            articleList = mapper.findArticleByTag(article);
        }else{
            articleList= mapper.findArticle(article);
        }

        return (PageInfo<Article>) new PageInfo(articleList);
    }

    @Override
    public List<Article> findArticle(Article article) {
        return  mapper.findArticle(article);
    }

    @Override
    public int updateArticle(Article article) {
        return mapper.updateArticle(article);
    }

    @Override
    public int deleteArticle(Article article) {
        return mapper.deleteArticle(article);
    }

    @Override
    public Article findArticleByArticleId(int articleId) {
        return mapper.findArticleByArticleId(articleId);
    }

    @Override
    public List<ArticleClassify> findArticleClassify() {
        return mapper.findArticleClassify();
    }

    @Override
    public int getArticleCount() {
        return mapper.getArticleCount();
    }

    @Override
    public List<Map<String,Object>>findArticleSevenDaysCount() {
        return mapper.findArticleSevenDaysCount();
    }

}
