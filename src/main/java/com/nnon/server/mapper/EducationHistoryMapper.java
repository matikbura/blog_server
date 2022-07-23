package com.nnon.server.mapper;

import com.nnon.server.pojo.EducationHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface EducationHistoryMapper {
    List<EducationHistory> findEducationHistoryByUid(int uid);

    Integer addEducationHistory(EducationHistory educationHistory);

    void updateEducationHistory(EducationHistory educationHistory);

    void deleteEducationHistory(EducationHistory educationHistory);
}
