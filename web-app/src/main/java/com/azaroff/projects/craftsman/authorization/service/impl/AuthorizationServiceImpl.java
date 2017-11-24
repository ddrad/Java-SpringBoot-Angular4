package com.azaroff.projects.craftsman.authorization.service.impl;

import com.azaroff.projects.craftsman.authorization.datalayer.dao.AuthorizationRepository;
import com.azaroff.projects.craftsman.authorization.datalayer.entity.AuthorizationEntity;
import com.azaroff.projects.craftsman.authorization.service.Authorization;
import com.azaroff.projects.craftsman.authorization.service.AuthorizationService;
import com.azaroff.projects.craftsman.authorization.service.convert.AuthorizationConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created by Dmitiy on 27.08.2016.
 */

@Service("authorizationService")
public class AuthorizationServiceImpl implements AuthorizationService {
    @Autowired
    private AuthorizationRepository dao;
    @Autowired
    @Qualifier("authorizationConvert")
    private AuthorizationConvert convert;

    @Override
    @Transactional
    public Authorization findByAuthID(String authorizationId) {
        if (StringUtils.isEmpty(authorizationId)) {
            return null;
        }
        int parseInt = Integer.parseInt(authorizationId);
        AuthorizationEntity authorizationEntity = dao.findById(parseInt);
        if (authorizationEntity == null) {
            return null;
        }
        return convert.toAuthorization(authorizationEntity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Authorization findByAuthData(String login, String password) {
        if (StringUtils.isEmpty(login) || StringUtils.isEmpty(password)) {
            return null;
        }
        AuthorizationEntity authorizationEntity = dao.findByEmailAndPassword(login, password);
        if (authorizationEntity == null) {
            return null;
        }
        return convert.toAuthorization(authorizationEntity);
    }

    @Override
    @Transactional
    public Authorization addAuthorizationData(Authorization authorization) {
        AuthorizationEntity entity = dao.save(convert.toAuthorizationEntity(authorization));
        return convert.toAuthorization(entity);
    }
}
