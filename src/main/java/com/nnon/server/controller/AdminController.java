package com.nnon.server.controller;

import com.nnon.server.comman.CommonResult;
import com.nnon.server.pojo.Admin;
import com.nnon.server.service.IAdminService;
import com.nnon.server.service.impl.AdminServiceImpl;
import com.nnon.server.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("admin")
@RestController
public class AdminController {
    @Autowired
    TokenUtil util;
    @Autowired
    IAdminService adminService;
    @RequestMapping("login")
    public CommonResult login(Admin admin){
        List<Admin> resultList =  adminService.findAdmin(admin);
        String token = "";
        if (!resultList.isEmpty()){
            token = util.sign(resultList.get(0).getAdminId(), "admin");
            resultList.get(0).setToken(token);
            resultList.get(0).setRole(2);
            return CommonResult.success(resultList.get(0));
        }
        return CommonResult.unauthorized("账号或密码错误");
    }
}
