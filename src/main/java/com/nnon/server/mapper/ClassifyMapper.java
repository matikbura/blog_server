package com.nnon.server.mapper;

import com.nnon.server.pojo.Classify;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface ClassifyMapper {
    public ArrayList<Classify> findClassify();
}
