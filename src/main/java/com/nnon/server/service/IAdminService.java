package com.nnon.server.service;

import com.nnon.server.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface IAdminService {
    List<Admin> findAdmin(Admin admin);

    Admin findAdminByAdminId(Integer adminId);
}
