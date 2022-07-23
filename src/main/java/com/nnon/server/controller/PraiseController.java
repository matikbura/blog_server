package com.nnon.server.controller;

import com.nnon.server.comman.CommonResult;
import com.nnon.server.pojo.Praise;
import com.nnon.server.service.IPraiseService;
import com.nnon.server.service.impl.PraiseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.jar.Pack200;

@RestController
@RequestMapping("praise")
public class PraiseController {
    //点赞服务
    @Autowired
    private IPraiseService praiseService;

    //添加点赞
    @RequestMapping("addPraise")
    public CommonResult addPraise(@RequestBody Praise praise){
        //通过用户id和文章id查询是否已经点赞
        Praise praise1 = praiseService.getPraiseByUserIdAndArticleId(praise);
        if(praise1 != null){
            //修改点赞状态
            praise1.setPraiseType(praise.getPraiseType());
            praiseService.updatePraise(praise1);
        }else{
            praise.setCreateTime(new Date());
            praiseService.addPraise(praise);
        }
        return CommonResult.success(praise);
    }
    //取消点赞
    @RequestMapping("cancelPraise")
    public CommonResult cancelPraise(@RequestBody Praise praise){
        praiseService.cancelPraise(praise);
        return CommonResult.success("cancel praise success");
    }
    @RequestMapping("findPraise")
    public CommonResult findPraise(@RequestBody Praise praise){
        List<Praise> praise1 = praiseService.findPraise(praise);
        return CommonResult.success(praise1);
    }
    @RequestMapping("deletePraise")
    public CommonResult deletePraise(@RequestBody Praise praise){
        praiseService.deletePraise(praise);
        return CommonResult.success("删除成功");
    }
}
