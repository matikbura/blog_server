package com.nnon.server.service;

import com.nnon.server.pojo.Praise;

import java.util.List;

public interface IPraiseService {
    void addPraise(Praise praise);

    void cancelPraise(Praise praise);

    Praise getPraiseByUserIdAndArticleId(Praise praise);

    void updatePraise(Praise praise1);

    Integer getPraiseCountByArticleId(Integer articleId);

    Integer getUnPraiseCountByArticleId(Integer articleId);

    List<Praise> findPraise(Praise praise);

    void deletePraise(Praise praise);
}
