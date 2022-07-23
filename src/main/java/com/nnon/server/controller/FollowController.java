package com.nnon.server.controller;

import com.github.pagehelper.PageInfo;
import com.nnon.server.comman.CommonResult;
import com.nnon.server.pojo.Article;
import com.nnon.server.pojo.Follow;
import com.nnon.server.pojo.Pager;
import com.nnon.server.pojo.User;
import com.nnon.server.service.FollowService;
import com.nnon.server.service.IFollowService;
import com.nnon.server.service.IUserService;
import com.nnon.server.service.impl.FollowServiceImpl;
import com.nnon.server.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("follow")
public class FollowController {
    //关注服务
    @Autowired
    private IFollowService followService;
    @Autowired
    private IUserService userService;
    //添加关注
    @RequestMapping("addFollow")
    public void addFollow(@RequestBody Follow follow){
        System.out.println(follow);
        follow.setCreateTime(new Date());
        followService.addFollow(follow);
    }
    //查找关注分页
    @RequestMapping("findFollowPage")
    public CommonResult findFollowPage(@RequestBody Pager<Follow> pager) throws ParseException {
        //查找关注且分页
        PageInfo<Follow> pageInfo= followService.findFollowPage(pager.getPage(),pager.getSize(),pager.getQueryParams());
        pager.setTotal(pageInfo.getTotal());
        pager.setRows(pageInfo.getList());
        for (Follow follow:pager.getRows()){
            //查找用户
            User followUser = userService.findUserByUid(follow.getFollowUid());
            followUser.setCreateTimeStr(DateTimeUtils.dateConvAppointStr(followUser.getCreateTime()));
            User user = userService.findUserByUid(follow.getUid());
            user.setCreateTimeStr(DateTimeUtils.dateConvAppointStr(user.getCreateTime()));
            follow.setFollowUser(followUser);
            follow.setUser(user);
        }
        pager.setSize(pageInfo.getPageSize());
        pager.setPage(pageInfo.getPageNum());
        return CommonResult.success(pager);
    }
    @RequestMapping("findFollow")
    public CommonResult findFollow(@RequestBody Follow follow){
        List<Follow> follow1 = followService.findFollow(follow);
        return CommonResult.success(follow1);
    }
    @RequestMapping("removeFollow")
    public void removeFollow(@RequestBody Follow follow){
        followService.removeFollow(follow);
    }

}
