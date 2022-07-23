package com.nnon.server.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Feedback {
    private Integer feedbackId;
    private String feedbackType;
    private String contact;
    private String fileUrl;
    private String[] fileUrlArray;
    private String plan;
    private String descript;
    private String createTimeStr;
    private Date createTime;
    private Date[] createTimeRange;
    private Integer status;
}
