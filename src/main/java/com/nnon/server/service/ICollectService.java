package com.nnon.server.service;

import com.github.pagehelper.PageInfo;
import com.nnon.server.pojo.Collect;
import com.nnon.server.pojo.Pager;

import java.util.List;

public interface ICollectService {
    void addCollect(Collect collect);

    PageInfo<Collect> findCollectPage(Pager<Collect> pager);

    void deleteCollect(Collect collect);

    List<Collect> findCollect(Collect collect);
}
