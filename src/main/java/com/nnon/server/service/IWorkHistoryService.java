package com.nnon.server.service;

import com.nnon.server.pojo.WorkHistory;

import java.util.List;

public interface IWorkHistoryService {
    List<WorkHistory> findWorkHistoryByUid(int uid);
}
