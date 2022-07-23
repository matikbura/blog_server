package com.nnon.server.controller;

import com.github.pagehelper.PageInfo;
import com.nnon.server.comman.CommonResult;
import com.nnon.server.pojo.*;
import com.nnon.server.service.IClassifyService;
import com.nnon.server.service.ITagService;
import com.nnon.server.service.impl.ClassifyServiceImpl;
import com.nnon.server.service.impl.TagServiceImpl;
import com.nnon.server.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping("tag")
public class TagController {
    @Autowired
    IClassifyService classifyService;
    @Autowired
    ITagService tagService ;


    @RequestMapping("findClassifyTagAll")
    public CommonResult findClassifyTagAll(){
        ArrayList<Classify> classifyList = classifyService.findClassify();
        for(Classify classify:classifyList){
            classify.setTagList(tagService.findTagByClassifyId(classify.getClassifyId()));
        }
        Map<Integer,List<Classify>> map = new HashMap<>();
        Iterator<Classify> iterator = classifyList.iterator();
        while (iterator.hasNext()){
            Classify classify = iterator.next();
            System.out.println(classify);
            //以父件号分组
            if(classify.getParentId()!=null&&classify.getParentId()!=0){
                if (map.containsKey(classify.getParentId())){
                    map.get(classify.getParentId()).add(classify);
                }
                else {
                    List<Classify> list = new ArrayList<>();
                    list.add(classify);
                    map.put(classify.getParentId(),list);
                }
                iterator.remove();
            }
        }
        for(Classify classify:classifyList){
            if (map.containsKey(classify.getClassifyId())){
                classify.setChildren(map.get(classify.getClassifyId()));
            }
        }
        return CommonResult.success(classifyList);
    }

    @RequestMapping("findTagPage")
    public CommonResult findTagPage(@RequestBody Pager<Tag> pager) throws ParseException {
        PageInfo<Tag> pageInfo= tagService.findTagPage(pager.getPage(),pager.getSize(),pager.getQueryParams());
        //获取文章标签
        pager.setRows(pageInfo.getList());
        pager.setTotal(pageInfo.getTotal());
        pager.setHasNextPage(pageInfo.isHasNextPage());
        pager.setSize(pageInfo.getPageSize());
        //转换时间格式
       for( Tag tag:pager.getRows()){
           tag.setCreateTimeStr(DateTimeUtils.dateConvAppointStr(tag.getCreateTime()));
           tag.setModifyTimeStr(DateTimeUtils.dateConvAppointStr(tag.getModifyTime()));

       }
        return CommonResult.success(pager);
    }
    @RequestMapping("updateTag")
    public CommonResult updateTag(@RequestBody Tag tag){
        tag.setModifyTime(new Date());
        tagService.updateTag(tag);
        return CommonResult.success("修改成功");
    }
    @RequestMapping("getTagCount")
    public CommonResult getTagCount(){
        return CommonResult.success(tagService.getTagCount());
    }
    @RequestMapping("findAllTag")
    public CommonResult findAllTag(){
        return CommonResult.success(tagService.findAllTag());
    }
    @RequestMapping("findAllClassify")
    public CommonResult findAllClassify(){
        return CommonResult.success(classifyService.findClassify());
    }
    @RequestMapping("addTag")
    public CommonResult addTag(@RequestBody Tag tag){
        tag.setCreateTime(new Date());
        tag.setModifyTime(new Date());
        tagService.addTag(tag);
        return CommonResult.success("添加成功");
    }
    @RequestMapping("deleteTag")
    public CommonResult deleteTag(@RequestBody Tag tag){
        tagService.deleteTag(tag);
        return CommonResult.success("删除成功");
    }
}
