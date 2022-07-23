package com.nnon.server.mapper;

import com.nnon.server.pojo.Article;
import com.nnon.server.pojo.ArticleClassify;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper {
    int addArticle(Article article);

    List<Article> findArticle(Article article);

    List<Article> findArticleByTag(Article article);

    int updateArticle(Article article);

    int deleteArticle(Article article);

    Article findArticleByArticleId(int articleId);

    List<ArticleClassify> findArticleClassify();

    int getArticleCount();

    List<Map<String,Object>> findArticleSevenDaysCount();
}
