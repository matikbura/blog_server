package com.nnon.server.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Classify {
    private  Integer classifyId;
    private String name;
    ArrayList<Tag> tagList;
    private Integer parentId;
    private List<Classify> children;
}
