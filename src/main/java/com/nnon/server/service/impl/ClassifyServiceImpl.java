package com.nnon.server.service.impl;

import com.nnon.server.mapper.ClassifyMapper;
import com.nnon.server.pojo.Classify;
import com.nnon.server.service.IClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClassifyServiceImpl implements IClassifyService {
    @Autowired
    ClassifyMapper mapper;
    @Override
    public ArrayList<Classify> findClassify() {
        return mapper.findClassify();
    }
}
