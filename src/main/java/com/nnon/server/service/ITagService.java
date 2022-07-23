package com.nnon.server.service;

import com.github.pagehelper.PageInfo;
import com.nnon.server.pojo.Tag;

import java.util.ArrayList;
import java.util.List;

public interface ITagService {
    ArrayList<Tag> findTagByClassifyId(Integer classify_id);

    Tag findTagByTagId(Integer tagId);

    PageInfo<Tag> findTagPage(int page, int size, Tag queryParams);

    void updateTag(Tag tag);

    int getTagCount();

    List<Tag> findAllTag();

    void addTag(Tag tag);

    void deleteTag(Tag tag);
}
