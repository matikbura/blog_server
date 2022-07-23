package com.nnon.server.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User {
    private Integer uid;
    private String username;
    private String password;
    private String name;
    private String token;

    private String sex;
    private String nativePlace;
    private String[] address;
    private String addressStr;
    private String phone;
    private String qq;
    private String vx;
    private String email;
    private String birthdayStr;
    private Date birthday;
    private String imgUrl;
    private String introduce;
    private int status;
    //工作经历
    List<WorkHistory> workHistoryList;
    //教育经历
    List<EducationHistory> educationHistoryList;


    private Date createTime;
    private String createTimeStr;
    private Date[] createTimeRange;
    private Date modifyTime;
    private String modifyTimeStr;
    private Date[] modifyTimeRange;
    private Date lastLoginTime;
    private String lastLoginTimeStr;
    private Date[] lastLoginTimeRange;
    private String checkCode;
    private Integer role;
}
