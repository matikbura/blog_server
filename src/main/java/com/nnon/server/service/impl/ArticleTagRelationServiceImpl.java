package com.nnon.server.service.impl;

import com.nnon.server.mapper.ArticleTagRelationMapper;
import com.nnon.server.pojo.ArticleTagRelation;
import com.nnon.server.service.IArticleTagRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleTagRelationServiceImpl implements IArticleTagRelationService {
    @Autowired
    ArticleTagRelationMapper mapper;
    @Override
    public void addArticleTagRelation(ArticleTagRelation relation) {
        mapper.addArticleTagRelation(relation);
    }

    @Override
    public List<ArticleTagRelation> findArticleTagRelationByArticleId(Integer articleId) {
        return mapper.findArticleTagRelationByArticleId(articleId);
    }
}
