package com.nnon.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nnon.server.mapper.CollectMapper;
import com.nnon.server.pojo.Collect;
import com.nnon.server.pojo.Pager;
import com.nnon.server.service.ICollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements ICollectService {
    @Autowired
    private CollectMapper collectMapper;
    @Override
    public void addCollect(Collect collect) {
        collectMapper.addCollect(collect);
    }

    @Override
    public PageInfo<Collect> findCollectPage(Pager<Collect> pager) {
        PageHelper.startPage(pager.getPage(),pager.getSize());
        List<Collect> collectList = collectMapper.findCollect(pager.getQueryParams());
        return new PageInfo<>(collectList);
    }

    @Override
    public void deleteCollect(Collect collect) {
        collectMapper.deleteCollect(collect);
    }

    @Override
    public List<Collect> findCollect(Collect collect) {
        return collectMapper.findCollect(collect);
    }
}
