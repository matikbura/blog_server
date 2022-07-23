package com.nnon.server.service;

import com.github.pagehelper.PageInfo;
import com.nnon.server.pojo.Notify;

public interface INotifyService {
    void addNotify(Notify notify);

    PageInfo<Notify> findNotifyPage(int size, int page, Notify queryParams);

    Notify findNotifyByNotifyId(Integer notifyId);

    void updateNotify(Notify notify);

    void deleteNotify(Integer notifyId);
}
