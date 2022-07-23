package com.nnon.server.mapper;

import com.github.pagehelper.PageInfo;
import com.nnon.server.comman.CommonResult;
import com.nnon.server.pojo.Follow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowMapper {
    void addFollow(Follow follow);

    List<Follow> findArticle(Follow queryParams);

    List<Follow> findFollow(Follow queryParams);

    void removeFollow(Follow follow);
}
