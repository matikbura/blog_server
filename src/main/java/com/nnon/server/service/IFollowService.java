package com.nnon.server.service;

import com.github.pagehelper.PageInfo;
import com.nnon.server.comman.CommonResult;
import com.nnon.server.pojo.Follow;

import java.util.List;

public interface IFollowService {
    public void addFollow(Follow follow);


    PageInfo<Follow> findFollowPage(int page, int size, Follow queryParams);

    List<Follow> findFollow(Follow follow);

    void removeFollow(Follow follow);
}
