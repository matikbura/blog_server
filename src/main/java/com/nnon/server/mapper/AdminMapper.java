package com.nnon.server.mapper;

import com.nnon.server.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AdminMapper {
    List<Admin> findAdmin(Admin admin);

    Admin findAdminByAdminId(Integer adminId);
}
