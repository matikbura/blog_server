package com.nnon.server.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Notify {
    private Integer notifyId;
    private String title;
    private String content;
    private Date createTime;
    private Date[] createTimeRange;
    private String coverUrl;
    private Integer adminId;
    private Date modifyTime;
    private Date[] modifyTimeRange;
    private String createTimeStr;
    private Admin admin;
    private Integer viewCount;
    private Integer likeCount;
}
