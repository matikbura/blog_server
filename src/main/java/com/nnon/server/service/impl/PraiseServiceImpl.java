package com.nnon.server.service.impl;

import com.nnon.server.mapper.PraiseMapper;
import com.nnon.server.pojo.Praise;
import com.nnon.server.service.IPraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PraiseServiceImpl implements IPraiseService {
    @Autowired
    private PraiseMapper praiseMapper;
    @Override
    public void addPraise(Praise praise) {
        praiseMapper.addPraise(praise);
    }

    @Override
    public void cancelPraise(Praise praise) {
        praiseMapper.cancelPraise(praise);
    }

    @Override
    public Praise getPraiseByUserIdAndArticleId(Praise praise) {
        return praiseMapper.getPraiseByUserIdAndArticleId(praise);
    }

    @Override
    public void updatePraise(Praise praise1) {
        praiseMapper.updatePraise(praise1);
    }

    @Override
    public Integer getPraiseCountByArticleId(Integer articleId) {
        return praiseMapper.getPraiseCountByArticleId(articleId);
    }

    @Override
    public Integer getUnPraiseCountByArticleId(Integer articleId) {
        return praiseMapper.getUnPraiseCountByArticleId(articleId);
    }

    @Override
    public List<Praise> findPraise(Praise praise) {
        return praiseMapper.findPraise(praise);
    }

    @Override
    public void deletePraise(Praise praise) {
        praiseMapper.deletePraise(praise);
    }

}
