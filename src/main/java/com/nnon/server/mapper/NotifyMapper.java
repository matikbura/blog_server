package com.nnon.server.mapper;

import com.nnon.server.pojo.Notify;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotifyMapper {
    void addNotify(Notify notify);

    List<Notify> findNotify(Notify notify);

    Notify findNotifyByNotifyId(Integer notifyId);

    void updateNotify(Notify notify);

    void deleteNotify(Integer notifyId);
}
