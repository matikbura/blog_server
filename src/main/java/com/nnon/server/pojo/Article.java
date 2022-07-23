package com.nnon.server.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

//文章实体
@Data
public class Article {
    //文章主内容
    private Integer articleId;
    private Integer uid;
    private String title;
    private String content;
    private String coverUrl;
    private String describes;
    private ArrayList<Tag> tags;
    private Tag tag;
    //文章状态
    private Integer status;
    private String  auditMessage;
    private Integer auditId;
    //创建时间
    private String createTimeStr;
    private Date createTime;
    private Date[] createTimeRange;

    //修改时间
    private String modifyTimeStr;
    private Date modifyTime;
    private Date[] modifyTimeRange;

    //发布时间
    private String releaseTimeStr;
    private Date releaseTime;
    private Date releaseTimeMin;
    private String releaseTimeMinStr;
    private Date releaseTimeMax;
    private String releaseTimeMaxStr;

    private Integer commonCount;
    private Integer collectCount;
    private Integer viewCount;
    private Integer praiseCount;
    private Integer unPraiseCount;
    //排序字段
    private String sortField;
    //是否反向
    private Integer isReverse;
    private Integer commentCount;
}
