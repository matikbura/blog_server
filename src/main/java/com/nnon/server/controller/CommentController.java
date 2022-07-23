package com.nnon.server.controller;

import com.github.pagehelper.PageInfo;
import com.nnon.server.comman.CommonResult;
import com.nnon.server.pojo.Comment;
import com.nnon.server.pojo.CommentClassify;
import com.nnon.server.pojo.Pager;
import com.nnon.server.service.ICommentService;
import com.nnon.server.service.IUserService;
import com.nnon.server.service.impl.CommentServiceImpl;
import com.nnon.server.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("comment")
@RestController
public class CommentController {
    @Autowired
    ICommentService commentService;
    @Autowired
    IUserService userService;
    @RequestMapping("addComment")
    public CommonResult addComment (@RequestBody Comment comment){
        System.out.println(comment);
        comment.setCreateTime(new Date());
        commentService.addComment(comment);
        return CommonResult.success(comment);
    }
    @RequestMapping("findComment")
    public CommonResult findComment(@RequestBody Comment comment) throws ParseException {
        List<Comment> commentList =  commentService.findComment(comment);
        System.out.println(comment);
        //收集根节点
        Map<Integer,Comment> firstComment = new HashMap<>();
        for(Comment commentTemp:commentList){
            //获取用户信息
            commentTemp.setUser(userService.findUserByUid(commentTemp.getUid()));
            if(commentTemp.getToUid()!=null){
                commentTemp.setToUser(userService.findUserByUid(commentTemp.getToUid()));
            }
            if(commentTemp.getParentId()==null){
                firstComment.put(commentTemp.getCommentId(),commentTemp);
            }
            commentTemp.setCreateTimeStr(DateTimeUtils.dateConvAppointStr(commentTemp.getCreateTime()));
        }
        //继续遍历获取跟节点的子节点
        Iterator<Comment> iterator = commentList.iterator();
        while(iterator.hasNext()){
            Comment commentTemp = iterator.next();
            if(commentTemp.getParentId()!=null){
                Comment comment1 = firstComment.get(commentTemp.getParentId());
                ArrayList<Comment> childNodeList = comment1.getChildNodeList();
                if(childNodeList!=null){
                    childNodeList.add(commentTemp);
                }else{
                    ArrayList<Comment> temp = new ArrayList<>();
                    temp.add(commentTemp);
                    comment1.setChildNodeList(temp);
                }
                iterator.remove();
            }
        }
        return CommonResult.success(commentList);
    }

    @RequestMapping("findCommentPage")
    public CommonResult findCommentPage(@RequestBody Pager<Comment> pager) throws ParseException {
        PageInfo<Comment> pageInfo =  commentService.findCommentPage(pager.getSize(),pager.getPage(),pager.getQueryParams());
        pager.setRows(pageInfo.getList());
        pager.setTotal(pageInfo.getTotal());
        pager.setHasNextPage(pageInfo.isHasNextPage());
        for(Comment comment:pager.getRows()){
            comment.setCreateTimeStr(DateTimeUtils.dateConvAppointStr(comment.getCreateTime()));
        }
        return CommonResult.success(pager);
    }
    @RequestMapping("updateComment")
    public CommonResult updateComment(@RequestBody Comment comment){
        commentService.updateComment(comment);
        return CommonResult.success("修改成功");
    }

    @RequestMapping("findCommentClassify")
    public CommonResult findCommentClassify(){
        List<CommentClassify> commentList =  commentService.findCommentClassify();
        for (CommentClassify commentClassify:commentList){
           switch (commentClassify.getStatus()){
               case 0:
                   commentClassify.setName("审核中");
                   break;
               case 1:
                   commentClassify.setName("审核通过");
                   break;
               case 2:
                   commentClassify.setName("审核不通过");
                   break;
           }
        }
        return CommonResult.success(commentList);
    }
    @RequestMapping("getCommentCount")
    public CommonResult getCommentCount(){
        int commentCount =  commentService.getCommentCount();
        return CommonResult.success(commentCount);
    }

    @RequestMapping("findCommentSevenDaysCount")
    public CommonResult findCommentSevenDaysCount(){
        List<Map<String,Object>>list = commentService.findArticleSevenDaysCount();
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
