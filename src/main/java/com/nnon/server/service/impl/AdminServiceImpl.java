package com.nnon.server.service.impl;

import com.nnon.server.mapper.AdminMapper;
import com.nnon.server.pojo.Admin;
import com.nnon.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    AdminMapper mapper;
    @Override
    public List<Admin> findAdmin(Admin admin) {
        return mapper.findAdmin(admin);
    }

    @Override
    public Admin findAdminByAdminId(Integer adminId) {
        return mapper.findAdminByAdminId(adminId);
    }
}
