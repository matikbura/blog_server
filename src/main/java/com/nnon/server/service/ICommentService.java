package com.nnon.server.service;

import com.github.pagehelper.PageInfo;
import com.nnon.server.pojo.Comment;
import com.nnon.server.pojo.CommentClassify;

import java.util.List;
import java.util.Map;

public interface ICommentService {
    void addComment(Comment comment);

    List<Comment> findComment(Comment comment);

    PageInfo<Comment> findCommentPage(int size, int page, Comment queryParams);

    void updateComment(Comment comment);

    List<CommentClassify> findCommentClassify();

    int getCommentCount();

    List<Map<String, Object>> findArticleSevenDaysCount();

    Integer getCommentCountByArticleId(Integer articleId);

}
