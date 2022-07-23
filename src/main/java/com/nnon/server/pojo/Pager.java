package com.nnon.server.pojo;
import lombok.Data;

import java.util.List;
@Data
public class Pager<T> {
    private int page;//分页起始页
    private int size;//每页记录数
    private T queryParams;//查询的实体类
    private List<T> rows;//返回的记录集合
    private long total;//总记录条数
    private boolean hasNextPage;//是否有下一页
}