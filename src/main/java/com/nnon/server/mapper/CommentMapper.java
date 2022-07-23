package com.nnon.server.mapper;

import com.nnon.server.pojo.Comment;
import com.nnon.server.pojo.CommentClassify;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommentMapper {
    void addComment(Comment comment);

    List<Comment> findComment(Comment comment);

    void updateComment(Comment comment);

    List<CommentClassify> findCommentClassify();

    int getCommentCount();

    List<Map<String, Object>> findArticleSevenDaysCount();

    Integer getCommentCountByArticleId(Integer articleId);
}
