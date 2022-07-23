package com.nnon.server.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class Comment {
    private Integer commentId;
    private Integer articleId;
    private Integer uid;
    private User user;
    private Integer parentId;
    private ArrayList<Comment> childNodeList;
    private String content;

    private Integer auditUid;
    private String auditMessage;

    private String createTimeStr;
    private Date createTime;
    private Date[] createTimeRange;
    private Integer toUid;
    private User toUser;

    private String sortField;
    private Integer isReverse;
    private Integer status;
}
