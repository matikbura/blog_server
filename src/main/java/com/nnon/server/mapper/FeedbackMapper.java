package com.nnon.server.mapper;

import com.nnon.server.pojo.Feedback;
import com.nnon.server.pojo.Pager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface FeedbackMapper {
    List<Map<String, Object>> findFeedbackSevenDaysCount();

    void addFeedback(Feedback feedback);


    List<Feedback> findFeedback(Feedback pager);

    void updateFeedback(Feedback feedback);
}
