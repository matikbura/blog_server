package com.nnon.server.pojo;
//关注实体

import lombok.Data;

import java.util.Date;
@Data
public class Follow {
    //关注Id
    private Integer followId;
    //关注者Id
    private Integer uid;
    private User user;
    //被关注者Id
    private Integer followUid;
    private User followUser;
    //关注时间
    private Date createTime;
    //关注时间字符串
    private String createTimeStr;
}
