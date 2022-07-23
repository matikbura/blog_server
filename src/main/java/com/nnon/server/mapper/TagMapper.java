package com.nnon.server.mapper;

import com.nnon.server.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface TagMapper {
    ArrayList<Tag> findTagByClassifyId(Integer classify_id);

    Tag findTagByTagId(Integer tagId);

    List<Tag> findTag(Tag queryParams);

    void updateTag(Tag tag);

    int getTagCount();

    List<Tag> findAllTag();

    void addTag(Tag tag);

    void deleteTag(Tag tag);
}
