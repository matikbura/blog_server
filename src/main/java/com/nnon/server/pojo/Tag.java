package com.nnon.server.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Tag {
    private Integer tagId;
    private String name;
    private Integer classifyId;
    private String tabColor;
    private String createTimeStr;
    private Date createTime;
    private String modifyTimeStr;
    private Date modifyTime;
    private Date[] createTimeRange;
    private Date[] modifyTimeRange;
}
