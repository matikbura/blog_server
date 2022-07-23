package com.nnon.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nnon.server.mapper.CommentMapper;
import com.nnon.server.pojo.Comment;
import com.nnon.server.pojo.CommentClassify;
import com.nnon.server.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    CommentMapper mapper;

    @Override
    public void addComment(Comment comment) {
        System.out.println(mapper);
        mapper.addComment(comment);
    }

    @Override
    public List<Comment> findComment(Comment comment) {
        return mapper.findComment(comment);
    }

    @Override
    public PageInfo<Comment> findCommentPage(int size, int page, Comment comment) {
        PageHelper.startPage(page,size);
        List<Comment> commentList = mapper.findComment(comment);
        return (PageInfo<Comment>) new PageInfo(commentList);
    }

    @Override
    public void updateComment(Comment comment) {
        mapper.updateComment(comment);
    }

    @Override
    public List<CommentClassify> findCommentClassify() {
        return mapper.findCommentClassify();
    }

    @Override
    public int getCommentCount() {
        return mapper.getCommentCount();
    }

    @Override
    public List<Map<String, Object>> findArticleSevenDaysCount() {
        return mapper.findArticleSevenDaysCount();
    }

    @Override
    public Integer getCommentCountByArticleId(Integer articleId) {
        return mapper.getCommentCountByArticleId(articleId);
    }
}
