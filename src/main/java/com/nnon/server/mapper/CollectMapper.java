package com.nnon.server.mapper;

import com.nnon.server.pojo.Collect;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectMapper {
    void addCollect(Collect collect);

    List<Collect> findCollect(Collect collect);

    void deleteCollect(Collect collect);
}
