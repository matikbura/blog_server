package com.nnon.server.service.impl;

import com.nnon.server.mapper.EducationHistoryMapper;
import com.nnon.server.pojo.EducationHistory;
import com.nnon.server.service.IEducationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EducationHistoryImpl implements IEducationHistoryService {
    @Autowired
    EducationHistoryMapper educationHistoryMapper;
    @Override
    public List<EducationHistory> findEducationHistoryByUid(int uid) {
        return educationHistoryMapper.findEducationHistoryByUid(uid);
    }

    @Override
    public Integer addEducationHistory(EducationHistory educationHistory) {
        return educationHistoryMapper.addEducationHistory(educationHistory);
    }

    @Override
    public void updateEducationHistory(EducationHistory educationHistory) {
        educationHistoryMapper.updateEducationHistory(educationHistory);
    }

    @Override
    public void deleteEducationHistory(EducationHistory educationHistory) {
        educationHistoryMapper.deleteEducationHistory(educationHistory);
    }
}
