package com.nnon.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nnon.server.mapper.FeedbackMapper;
import com.nnon.server.pojo.Feedback;
import com.nnon.server.pojo.Pager;
import com.nnon.server.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
@Service
public class FeedbackServiceImpl implements IFeedbackService {
    @Autowired
    private FeedbackMapper mapper;
    @Override
    public List<Map<String, Object>> findFeedbackSevenDaysCount() {
        return mapper.findFeedbackSevenDaysCount();
    }

    @Override
    public void addFeedback(Feedback feedback) {
        mapper.addFeedback(feedback);
    }

    @Override
    public PageInfo<Feedback> findFeedbackByPage(Pager<Feedback> pager) {
        PageHelper.startPage(pager.getPage(), pager.getSize());
        PageInfo<Feedback> pageInfo = new PageInfo<Feedback>(mapper.findFeedback(pager.getQueryParams()));
        return pageInfo;
    }

    @Override
    public void updateFeedback(Feedback feedback) {
        System.out.println(feedback);
        mapper.updateFeedback(feedback);
    }
}
