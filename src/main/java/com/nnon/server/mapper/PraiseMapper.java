package com.nnon.server.mapper;

import com.nnon.server.pojo.Praise;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PraiseMapper {
    public void addPraise(Praise praise);
    public void cancelPraise(Praise praise);
    Praise getPraiseByUserIdAndArticleId(Praise praise);

    void updatePraise(Praise praise1);

    Integer getPraiseCountByArticleId(Integer articleId);

    Integer getUnPraiseCountByArticleId(Integer articleId);

    List<Praise> findPraise(Praise praise);

    void deletePraise(Praise praise);
}
