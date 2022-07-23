package com.nnon.server.controller;

import com.github.pagehelper.PageInfo;
import com.nnon.server.comman.CommonResult;
import com.nnon.server.pojo.*;
import com.nnon.server.service.*;
import com.nnon.server.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("article")
public class ArticleController {
    //文章服务
    @Autowired
    IArticleService articleService;
    @Autowired
    IArticleTagRelationService articleTagRelationService;
    @Autowired
    ITagService tagService;
    @Autowired
    ICommentService commentService;
    @Autowired
    IPraiseService praiseService;
    //添加文章
    @RequestMapping("addArticle")
    public CommonResult addArticle(@RequestBody Article article, HttpServletRequest request) {
        //初始化状态
        article.setModifyTime(new Date());
        article.setCreateTime(new Date());
        Object verifyUid = request.getAttribute("verifyUid");
        int uid = Integer.parseInt(verifyUid.toString());
        article.setUid(uid);
        articleService.addArticle(article);
        article.getArticleId();
        ArticleTagRelation relation = null;
        for (Tag tag : article.getTags()) {
            relation = new ArticleTagRelation();
            relation.setArticleId(article.getArticleId());
            relation.setTagId(tag.getTagId());
            articleTagRelationService.addArticleTagRelation(relation);
        }
        return CommonResult.success("添加成功");
    }

    //查找文章分页
    @RequestMapping("findArticlePage")
    public CommonResult findArticlePage(@RequestBody Pager<Article> pager) throws ParseException {
        //查找文章且分页
        PageInfo<Article> pageInfo = articleService.findArticlePage(pager.getPage(), pager.getSize(), pager.getQueryParams());
        //获取文章标签
        for (Article article : pageInfo.getList()) {
            List<ArticleTagRelation> articleTagRelations = articleTagRelationService.findArticleTagRelationByArticleId(article.getArticleId());
            ArrayList<Tag> tagList = new ArrayList<>();
            for (ArticleTagRelation articleTagRelation : articleTagRelations) {
                tagList.add(tagService.findTagByTagId(articleTagRelation.getTagId()));
            }
            article.setCreateTimeStr(DateTimeUtils.dateConvAppointStr(article.getCreateTime()));
            article.setTags(tagList);
            article.setModifyTimeStr(DateTimeUtils.dateConvAppointStr(article.getModifyTime()));
            article.setCommentCount(commentService.getCommentCountByArticleId(article.getArticleId()));
        }
        //获取点赞数
        for (Article article : pageInfo.getList()) {
            article.setPraiseCount(praiseService.getPraiseCountByArticleId(article.getArticleId()));
            article.setUnPraiseCount(praiseService.getUnPraiseCountByArticleId(article.getArticleId()));
        }
        pager.setRows(pageInfo.getList());
        pager.setTotal(pageInfo.getTotal());
        pager.setHasNextPage(pageInfo.isHasNextPage());
        return CommonResult.success(pager);
    }

    //查找所有文章
    @RequestMapping("findArticle")
    public CommonResult findArticle(@RequestBody Article article) throws ParseException {
        List<Article> articleList = articleService.findArticle(article);
        for (Article result : articleList) {
            List<ArticleTagRelation> articleTagRelations = articleTagRelationService.findArticleTagRelationByArticleId(article.getArticleId());
            ArrayList<Tag> tagList = new ArrayList<>();
            for (ArticleTagRelation articleTagRelation : articleTagRelations) {
                tagList.add(tagService.findTagByTagId(articleTagRelation.getTagId()));
            }
            result.setCreateTimeStr(DateTimeUtils.dateConvAppointStr(result.getCreateTime()));
            result.setTags(tagList);
        }
        for (Article article1 : articleList) {
            article1.setPraiseCount(praiseService.getPraiseCountByArticleId(article.getArticleId()));
            article1.setUnPraiseCount(praiseService.getUnPraiseCountByArticleId(article.getArticleId()));
        }
        return CommonResult.success(articleList);
    }
    @RequestMapping("findArticleByArticleId")
    public CommonResult findArticleByArticleId(@RequestBody Article article) throws ParseException {
        Article result = articleService.findArticleByArticleId(article.getArticleId());
        //浏览+1
        result.setViewCount(result.getViewCount()+1);
        articleService.updateArticle(result);
        List<ArticleTagRelation> articleTagRelations = articleTagRelationService.findArticleTagRelationByArticleId(article.getArticleId());
        ArrayList<Tag> tagList = new ArrayList<>();
        for (ArticleTagRelation articleTagRelation : articleTagRelations) {
            tagList.add(tagService.findTagByTagId(articleTagRelation.getTagId()));
        }
        result.setCreateTimeStr(DateTimeUtils.dateConvAppointStr(result.getCreateTime()));
        result.setTags(tagList);
        result.setPraiseCount(praiseService.getPraiseCountByArticleId(result.getArticleId()));
        result.setUnPraiseCount(praiseService.getUnPraiseCountByArticleId(result.getArticleId()));
        return CommonResult.success(result);
    }

    //更新文章
    @RequestMapping("updateArticle")
    public CommonResult updateArticle(@RequestBody Article article) {
        int i = articleService.updateArticle(article);
        return CommonResult.success("更新成功");
    }

    //删除文章
    @RequestMapping("deleteArticle")
    public CommonResult deleteArticle(@RequestBody Article article) {
        int i = articleService.deleteArticle(article);
        return CommonResult.success("删除成功");
    }

    @RequestMapping("findArticleClassify")
    public CommonResult findArticleClassify() {
        List<ArticleClassify> articleList = articleService.findArticleClassify();
        for (ArticleClassify article : articleList) {
            switch (article.getStatus()) {
                case 0:
                    article.setName("草稿箱");
                    break;
                case 1:
                    article.setName("审核中");
                    break;
                case 2:
                    article.setName("审核通过");
                    break;
                case 3:
                    article.setName("审核不通过");
                    break;
                case 4:
                    article.setName("已发布");
                    break;
                case 5:
                    article.setName("回收站");
                    break;
            }

        }
        return CommonResult.success(articleList);
    }
    @RequestMapping("getArticleCount")
    public CommonResult getArticleCount(){
        int count = articleService.getArticleCount();
        return CommonResult.success(count);
    }
    @RequestMapping("findArticleSevenDaysCount")
    public CommonResult findCommentSevenDaysCount(){
        List<Map<String,Object>>list = articleService.findArticleSevenDaysCount();
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
