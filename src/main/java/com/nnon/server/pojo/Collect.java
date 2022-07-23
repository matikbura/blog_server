package com.nnon.server.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Collect {
    //收藏id
    private Integer collectId;
    //用户id
    private Integer uid;
    //文章id
    private Integer articleId;
    private Article article;
    //收藏时间
    private String createTimeStr;
    private Date createTime;
}
