package com.nnon.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nnon.server.mapper.TagMapper;
import com.nnon.server.pojo.Article;
import com.nnon.server.pojo.Tag;
import com.nnon.server.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements ITagService {
    @Autowired
    TagMapper mapper;
    @Override
    public ArrayList<Tag> findTagByClassifyId(Integer classify_id) {
        return mapper.findTagByClassifyId(classify_id);
    }

    @Override
    public Tag findTagByTagId(Integer tagId) {
        return mapper.findTagByTagId(tagId);
    }

    @Override
    public PageInfo<Tag> findTagPage(int page, int size, Tag queryParams) {
        PageHelper.startPage(page,size);
        List<Tag> tagList = mapper.findTag(queryParams);
        return (PageInfo<Tag>) new PageInfo(tagList);
    }

    @Override
    public void updateTag(Tag tag) {
        mapper.updateTag(tag);
    }

    @Override
    public int getTagCount() {
        return mapper.getTagCount();
    }

    @Override
    public List<Tag> findAllTag() {
        return mapper.findAllTag();
    }

    @Override
    public void addTag(Tag tag) {
        mapper.addTag(tag);
    }

    @Override
    public void deleteTag(Tag tag) {
        mapper.deleteTag(tag);
    }
}
