package com.nnon.server.mapper;

import com.nnon.server.pojo.WorkHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkHistoryMapper {
    List<WorkHistory> findWorkHistoryByUid(Integer uid);
}
