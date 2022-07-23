package com.nnon.server.controller;

import com.github.pagehelper.PageInfo;
import com.nnon.server.comman.CommonResult;
import com.nnon.server.pojo.*;
import com.nnon.server.service.IFeeabackService;
import com.nnon.server.service.IFeedbackService;
import com.nnon.server.service.impl.FeedbackServiceImpl;
import com.nnon.server.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("feedback")
public class FeedbackController {
    @Autowired
    private IFeedbackService feedbackService;
    @RequestMapping("addFeedback")
    public CommonResult addFeedback(@RequestBody Feedback feedback) {
        // TODO
        feedback.setCreateTime(new Date());
        feedback.setStatus(0);
        String[] fileUrlArray = feedback.getFileUrlArray();
        String fileUrl = "";
        if(fileUrlArray != null && fileUrlArray.length > 0){
            for(String url:fileUrlArray){
                fileUrl += url + ",";
            }
        }
        feedback.setFileUrl(fileUrl);
        feedbackService.addFeedback(feedback);
        return CommonResult.success("添加成功");
    }

    @RequestMapping("findFeedbackByPage")
    public CommonResult findFeedbackByPage(@RequestBody Pager<Feedback> pager) {
        //查找文章且分页
        PageInfo<Feedback> pageInfo = feedbackService.findFeedbackByPage(pager);
        pager.setRows(pageInfo.getList());
        pager.setTotal(pageInfo.getTotal());
        pager.setHasNextPage(pageInfo.isHasNextPage());
        pager.getRows().forEach(feedback -> {
            String[] fileUrlArray = feedback.getFileUrl().split(",");
            feedback.setFileUrlArray(fileUrlArray);
            try {
                feedback.setCreateTimeStr(DateTimeUtils.dateConvAppointStr(feedback.getCreateTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return CommonResult.success(pager);
    }

    @RequestMapping("updateFeedback")
    public CommonResult updateFeedback(@RequestBody  Feedback feedback) {
        feedbackService.updateFeedback(feedback);
        return CommonResult.success("更新成功");
    }
    @RequestMapping("deleteFeedback")
    public CommonResult deleteFeedback() {
        // TODO
        return null;
    }
    @RequestMapping("findFeedbackSevenDaysCount")
    public CommonResult findFeedbackSevenDaysCount() {
        // TODO
        List<Map<String,Object>> list = feedbackService.findFeedbackSevenDaysCount();
        //获取某一天日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前日期
        Date date = new Date();
        //获取当前日期的前七天日期
        ArrayList<Integer> dateList = new ArrayList<>();
        for(int i = 6 ; i >= 0 ; i--){
            String date1 = DateTimeUtils.getDateBefore(date,i);
            Integer flag = 0;
            for(Map<String,Object>map:list){
                if(map.get("days").equals(date1)){
                    flag = ((Long) map.get("count")).intValue();
                }
            }
            dateList.add(flag);
        }
        return CommonResult.success(dateList);
    }

}
