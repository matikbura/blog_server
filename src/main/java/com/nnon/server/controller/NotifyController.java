package com.nnon.server.controller;

import com.github.pagehelper.PageInfo;
import com.nnon.server.comman.CommonResult;
import com.nnon.server.pojo.Article;
import com.nnon.server.pojo.Notify;
import com.nnon.server.pojo.Pager;
import com.nnon.server.service.IAdminService;
import com.nnon.server.service.INotifyService;
import com.nnon.server.service.impl.NotifyServiceImpl;
import com.nnon.server.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;

@RestController
@RequestMapping("notify")
public class NotifyController {
    @Autowired
    INotifyService notifyService;
    @Autowired
    IAdminService adminService;
    @RequestMapping("addNotify")
    public CommonResult addNotify(Notify notify){
        notify.setCreateTime(new Date());
        notify.setModifyTime(new Date());
        notifyService.addNotify(notify);
        return CommonResult.success(notify);
    }

    @RequestMapping("findNotifyPage")
    public CommonResult findNotifyPage(@RequestBody Pager<Notify> pager) throws ParseException {
        PageInfo<Notify> pageInfo = notifyService.findNotifyPage(pager.getSize(),pager.getPage(),pager.getQueryParams());
        pager.setRows(pageInfo.getList());
        pager.setTotal(pageInfo.getTotal());
        pager.setHasNextPage(pageInfo.isHasNextPage());
        for(Notify notify:pager.getRows()){
            notify.setCreateTimeStr(DateTimeUtils.dateConvAppointStr(notify.getCreateTime()));
        }
        pager.setSize(pageInfo.getPageSize());
        return CommonResult.success(pager);
    }
    @RequestMapping("findNotifyByNotifyId")
    public CommonResult findNotifyByNotifyId(Integer notifyId) throws ParseException {
        Notify notify = notifyService.findNotifyByNotifyId(notifyId);
        notify.setCreateTimeStr(DateTimeUtils.dateConvAppointStr(notify.getCreateTime()));
        notify.setAdmin(adminService.findAdminByAdminId(notify.getAdminId()));
        notify.setViewCount(notify.getViewCount()+1);
        Notify notify1 = new Notify();
        notify1.setNotifyId(notify.getNotifyId());
        notify1.setViewCount(notify.getViewCount());
        notifyService.updateNotify(notify1);
        return CommonResult.success(notify);
    }
    @RequestMapping("updateNotify")
    public CommonResult updateNotify(@RequestBody Notify notify){
        notify.setModifyTime(new Date());
        notifyService.updateNotify(notify);
        return CommonResult.success(notify);
    }
    @RequestMapping("deleteNotify")
    public CommonResult deleteNotify(@RequestBody Notify notify){
        notifyService.deleteNotify(notify.getNotifyId());
        return CommonResult.success("删除成功");
    }
}
