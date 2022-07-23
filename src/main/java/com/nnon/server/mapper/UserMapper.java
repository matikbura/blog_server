package com.nnon.server.mapper;

import com.nnon.server.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    public User login(User user);

    User findUserByUsername(String username);

    Integer register(User user);

    User findUserByUid(int uid);

    void updateUser(User user);

    ArrayList<User> findUser(User user);

    int findUserBanCount();

    int findUserLoginCount();

    int findUserCount();

    List<Map<String, Object>> findArticleSevenDaysCount();

    List<User> findUserByFuzzy(User queryParams);

}
