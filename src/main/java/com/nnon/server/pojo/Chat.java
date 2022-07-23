package com.nnon.server.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class Chat {
    private Integer chatId;
    private Integer fromUid;
    private Integer toUid;
    private String isRead;
    private String context;
    private String createTimeStr;
    private Date createTime;
}
