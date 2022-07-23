package com.nnon.server.service;

import com.github.pagehelper.PageInfo;
import com.nnon.server.pojo.Article;
import com.nnon.server.pojo.ArticleClassify;

import java.util.List;
import java.util.Map;

public interface IArticleService {
    int addArticle(Article article);

    PageInfo<Article> findArticlePage(int page, int size, Article article);

    List<Article> findArticle(Article article);

    int updateArticle(Article article);

    int deleteArticle(Article article);

    Article findArticleByArticleId(int articleId);

    List<ArticleClassify> findArticleClassify();

    int getArticleCount();


    List<Map<String,Object>> findArticleSevenDaysCount();

}
