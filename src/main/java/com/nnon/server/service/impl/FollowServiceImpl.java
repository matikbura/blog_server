package com.nnon.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nnon.server.comman.CommonResult;
import com.nnon.server.mapper.FollowMapper;
import com.nnon.server.pojo.Follow;
import com.nnon.server.service.IFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements IFollowService {
    @Autowired
    private FollowMapper followMapper;
    @Override
    public void addFollow(Follow follow) {
        followMapper.addFollow(follow);
    }


    @Override
    public PageInfo<Follow> findFollowPage(int page, int size, Follow queryParams) {
        PageHelper.startPage(page,size);
        return new PageInfo<>(followMapper.findFollow(queryParams));
    }

    @Override
    public List<Follow> findFollow(Follow follow) {
        return followMapper.findFollow(follow);
    }

    @Override
    public void removeFollow(Follow follow) {
        followMapper.removeFollow(follow);
    }
}
