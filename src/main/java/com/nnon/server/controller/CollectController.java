package com.nnon.server.controller;

import com.github.pagehelper.PageInfo;
import com.nnon.server.comman.CommonResult;
import com.nnon.server.pojo.*;
import com.nnon.server.service.IArticleService;
import com.nnon.server.service.IArticleTagRelationService;
import com.nnon.server.service.ICollectService;
import com.nnon.server.service.ITagService;
import com.nnon.server.service.impl.CollectServiceImpl;
import com.nnon.server.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("collect")
public class CollectController {
    @Autowired
    private ICollectService collectService;
    @Autowired
    private IArticleService articleService;
    @Autowired
    private ITagService tagService;
    @Autowired
    private IArticleTagRelationService articleTagRelationService;
    @RequestMapping("addCollect")
    public CommonResult addCollect(@RequestBody Collect collect){
        collect.setCreateTime(new Date());
        collectService.addCollect(collect);
        return CommonResult.success("添加成功");
    }
    //查找收藏分页
    @RequestMapping("findCollectPage")
    public CommonResult findCollectPage(@RequestBody Pager<Collect> pager) throws ParseException {
        //查找关注且分页
        PageInfo<Collect> pageInfo= collectService.findCollectPage(pager);
        pager.setTotal(pageInfo.getTotal());
        pager.setRows(pageInfo.getList());
        for (Collect collect:pager.getRows()){
            int articleId = collect.getArticleId();
            //查找文章
            Article article = articleService.findArticleByArticleId(articleId);
            article.setCreateTimeStr(DateTimeUtils.dateConvAppointStr(article.getCreateTime()));
            List<ArticleTagRelation> articleTagRelation = articleTagRelationService.findArticleTagRelationByArticleId(article.getArticleId());
            //查找标签
            ArrayList<Tag> tagList = new ArrayList<>();
            for(ArticleTagRelation articleTagRelation1:articleTagRelation){
                int tagId = articleTagRelation1.getTagId();
                Tag tag = tagService.findTagByTagId(tagId);
                tagList.add(tag);
            }
            article.setTags(tagList);
            collect.setArticle(article);
        }
        pager.setSize(pageInfo.getPageSize());
        pager.setPage(pageInfo.getPageNum());
        return CommonResult.success(pager);
    }
    //删除收藏
    @RequestMapping("deleteCollect")
    public CommonResult deleteCollect(@RequestBody Collect collect){
        collectService.deleteCollect(collect);
        return CommonResult.success("删除成功");
    }
    @RequestMapping("findCollect")
    public CommonResult findCollect(@RequestBody Collect collect){
        List<Collect> collect1 = collectService.findCollect(collect);
        return CommonResult.success(collect1);
    }
}


