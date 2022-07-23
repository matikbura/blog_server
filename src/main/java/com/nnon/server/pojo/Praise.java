package com.nnon.server.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Praise {
    // 点赞id
    private Integer praiseId;
    // 点赞用户id
    private Integer uid;
    // 点赞的文章id
    private Integer articleId;
    // 点赞时间
    private String createTimeStr;
    private Date createTime;
    // 点赞状态
    private Integer praiseType;

}
