package com.nnon.server.service;

import com.github.pagehelper.PageInfo;
import com.nnon.server.pojo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IUserService {
    User login(User user);

    User findUserByUsername(String username);

    Integer register(User user);

    User findUserByUid(int uid);

    void updateUser(User user);

    ArrayList<User> findUser(User user);

    PageInfo<User> findUserPage(int page, int size, User queryParams);

    int findUserBanCount();

    int findUserLoginCount();

    int findUserCount();

    List<Map<String, Object>> findArticleSevenDaysCount();

    PageInfo<User> findUserByFuzzyPage(int page, int size, User queryParams);
}
