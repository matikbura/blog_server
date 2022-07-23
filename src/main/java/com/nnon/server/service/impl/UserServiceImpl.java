package com.nnon.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nnon.server.mapper.UserMapper;
import com.nnon.server.pojo.Article;
import com.nnon.server.pojo.User;
import com.nnon.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper mapper;
    @Override
    public User login(User user) {
        return mapper.login(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return mapper.findUserByUsername(username);
    }

    @Override
    public Integer register(User user) {
        return mapper.register(user);
    }

    @Override
    public User findUserByUid(int uid) {
        return mapper.findUserByUid(uid);
    }

    @Override
    public void updateUser(User user) {
        mapper.updateUser(user);
    }

    @Override
    public ArrayList<User> findUser(User user) {
        return mapper.findUser(user);
    }

    @Override
    public PageInfo<User> findUserPage(int page, int size, User queryParams) {
        PageHelper.startPage(page,size);//分页起始码以及每页页数
        List<User> userList= mapper.findUser(queryParams);
        return (PageInfo<User>) new PageInfo(userList);
    }

    @Override
    public int findUserBanCount() {
        return mapper.findUserBanCount();
    }

    @Override
    public int findUserLoginCount() {
        return mapper.findUserLoginCount();
    }

    @Override
    public int findUserCount() {
        return mapper.findUserCount();
    }

    @Override
    public List<Map<String, Object>> findArticleSevenDaysCount() {
        return mapper.findArticleSevenDaysCount();
    }

    @Override
    public PageInfo<User> findUserByFuzzyPage(int page, int size, User queryParams) {
        PageHelper.startPage(page,size);//分页起始码以及每页页数
        List<User> userList= mapper.findUserByFuzzy(queryParams);
        return (PageInfo<User>) new PageInfo(userList);
    }

}
