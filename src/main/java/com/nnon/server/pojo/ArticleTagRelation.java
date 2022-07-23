package com.nnon.server.pojo;

import lombok.Data;

@Data
public class ArticleTagRelation {
    private Integer atRelationId;
    private Integer articleId;
    private Integer tagId;
}
