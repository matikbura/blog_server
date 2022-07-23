package com.nnon.server.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class WorkHistory {
    private Integer workId;
    private String companyName;
    private String jobTitle;
    private String industry;
    private String enterTimeStr;
    private Integer uid;
}
