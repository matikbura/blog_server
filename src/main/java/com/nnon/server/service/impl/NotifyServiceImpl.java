package com.nnon.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nnon.server.mapper.NotifyMapper;
import com.nnon.server.pojo.Article;
import com.nnon.server.pojo.Notify;
import com.nnon.server.service.INotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class NotifyServiceImpl implements INotifyService {
    @Autowired
    NotifyMapper mapper;
    @Override
    public void addNotify(Notify notify) {
        mapper.addNotify(notify);
    }

    @Override
    public PageInfo<Notify> findNotifyPage(int size, int page, Notify notify) {
        PageHelper.startPage(page,size);//分页起始码以及每页页数
       List<Notify> notifyList= mapper.findNotify(notify);

        return (PageInfo<Notify>) new PageInfo(notifyList);
    }

    @Override
    public Notify findNotifyByNotifyId(Integer notifyId) {
        return mapper.findNotifyByNotifyId(notifyId);
    }

    @Override
    public void updateNotify( Notify notify) {
        mapper.updateNotify(notify);
    }

    @Override
    public void deleteNotify(Integer notifyId) {
        mapper.deleteNotify(notifyId);
    }
}
