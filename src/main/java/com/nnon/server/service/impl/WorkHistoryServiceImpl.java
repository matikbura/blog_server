package com.nnon.server.service.impl;

import com.nnon.server.mapper.WorkHistoryMapper;
import com.nnon.server.pojo.WorkHistory;
import com.nnon.server.service.IWorkHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WorkHistoryServiceImpl implements IWorkHistoryService {
    @Autowired
    WorkHistoryMapper mapper;
    @Override
    public List<WorkHistory> findWorkHistoryByUid(int uid) {
        return mapper.findWorkHistoryByUid(uid);
    }
}
