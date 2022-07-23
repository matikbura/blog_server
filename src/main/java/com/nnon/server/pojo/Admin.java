package com.nnon.server.pojo;

import lombok.Data;

@Data
public class Admin {
    private Integer adminId;
    private String username;
    private String password;
    private String name;
    private String token;
    private Integer role;
}
