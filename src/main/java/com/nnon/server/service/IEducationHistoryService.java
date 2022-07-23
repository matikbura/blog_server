package com.nnon.server.service;

import com.nnon.server.pojo.EducationHistory;

import java.util.List;

public interface IEducationHistoryService {
    List<EducationHistory> findEducationHistoryByUid(int uid);

    Integer addEducationHistory(EducationHistory educationHistory);

    void updateEducationHistory(EducationHistory educationHistory);

    void deleteEducationHistory(EducationHistory educationHistory);
}
