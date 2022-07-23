package com.nnon.server.controller;

import com.nnon.server.comman.CommonResult;
import com.nnon.server.pojo.EducationHistory;
import com.nnon.server.service.IEducationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("educationHistory")
public class EducationHistoryController {
    @Autowired
    IEducationHistoryService educationHistoryService;
    @RequestMapping("addEducationHistory")
    @ResponseBody
    public CommonResult addEducationHistory(EducationHistory educationHistory, HttpServletRequest request){
        Object verifyUid = request.getAttribute("verifyUid");
        int uid = Integer.parseInt(verifyUid.toString());
        educationHistory.setUid(uid);
        Integer educationId = educationHistoryService.addEducationHistory(educationHistory);
        educationHistory.setEducationId(educationId);
        return CommonResult.success(educationHistory);
    }
    @RequestMapping("updateEducationHistory")
    @ResponseBody
    public CommonResult updateEducationHistory(EducationHistory educationHistory, HttpServletRequest request){
        educationHistoryService.updateEducationHistory(educationHistory);
        return CommonResult.success(educationHistory);
    }
    @RequestMapping("deleteEducationHistory")
    @ResponseBody
    public CommonResult deleteEducationHistory(EducationHistory educationHistory){
        educationHistoryService.deleteEducationHistory(educationHistory);
        return CommonResult.success("删除成功");
    }
}
