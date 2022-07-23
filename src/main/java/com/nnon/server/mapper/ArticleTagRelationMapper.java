package com.nnon.server.mapper;

import com.nnon.server.pojo.ArticleTagRelation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleTagRelationMapper {
    void addArticleTagRelation(ArticleTagRelation relation);

    List<ArticleTagRelation> findArticleTagRelationByArticleId(Integer articleId);
}
