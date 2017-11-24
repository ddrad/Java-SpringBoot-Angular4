package com.azaroff.projects.craftsman.token.datalayer.dao.impl;

import com.azaroff.projects.craftsman.token.datalayer.dao.TokenDataCustomRepository;
import com.azaroff.projects.craftsman.token.datalayer.entity.TokenDataEntity;
import com.azaroff.projects.craftsman.token.service.TokenDataStatus;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by Dmitiy on 24.08.2016.
 */

public class TokenDataCustomRepositoryImpl implements TokenDataCustomRepository {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    @Transactional
    public void updateTokenDataEntities(List<Integer> tokenDataIdList, TokenDataStatus tokenStatus) {
//        Criteria criteria = getSession().createCriteria(TokenDataEntity.class)
//                .add(Restrictions.in("id", tokenDataIdList));
//        List<TokenDataEntity> tokenDataEntities = criteria.list();
//        for (TokenDataEntity tokenDataEntity : tokenDataEntities) {
//            tokenDataEntity.setStatus(tokenStatus);
//            persist(tokenDataEntity);
//        }
    }

    @Override
    @Transactional
    public void removeExpiredTokenData() {
//        Criteria criteria = getSession().createCriteria(TokenDataEntity.class);
//        criteria.add(Restrictions.le("expirationTime", new Date()));
//        criteria.add(Restrictions.eq("removeAfterExpiration", "true"));
//        List<TokenDataEntity> entities = criteria.list();
//        for (TokenDataEntity entity : entities) {
//            delete(entity);
//        }
    }
}
