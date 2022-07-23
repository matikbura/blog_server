package com.nnon.server.service;

import com.nnon.server.pojo.ArticleTagRelation;

import java.util.List;

public interface IArticleTagRelationService {
    void addArticleTagRelation(ArticleTagRelation relation);

    List<ArticleTagRelation> findArticleTagRelationByArticleId(Integer articleId);
}
