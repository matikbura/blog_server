package com.nnon.server.service;

import com.github.pagehelper.PageInfo;
import com.nnon.server.pojo.Feedback;
import com.nnon.server.pojo.Pager;

import java.util.List;
import java.util.Map;

public interface IFeedbackService {

    List<Map<String, Object>> findFeedbackSevenDaysCount();

    void addFeedback(Feedback feedback);

    PageInfo<Feedback> findFeedbackByPage(Pager<Feedback> pager);

    void updateFeedback(Feedback feedback);
}
