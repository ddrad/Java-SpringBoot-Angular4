package com.azaroff.projects.craftsman.authorization.service.convert;


import com.azaroff.projects.craftsman.authorization.datalayer.entity.AuthorizationEntity;
import com.azaroff.projects.craftsman.authorization.service.Authorization;
import org.springframework.stereotype.Service;

/**
 * Created by AzarovD on 25.08.2016.
 */

@Service("authorizationConvert")
public class AuthorizationConvert {

    public Authorization toAuthorization(AuthorizationEntity authorizationEntity) {
        Authorization authorizationInfo = new Authorization();
        authorizationInfo.setLogin(authorizationEntity.getEmail());
        authorizationInfo.setPassword(authorizationEntity.getPassword());
        authorizationInfo.setCustomerId(authorizationEntity.getCustomerId());
        return authorizationInfo;
    }

    public AuthorizationEntity toAuthorizationEntity(Authorization authorization) {
        AuthorizationEntity entity = new AuthorizationEntity();
        entity.setPassword(authorization.getPassword());
        entity.setCustomerId(authorization.getCustomerId());
        entity.setEmail(authorization.getLogin());
        return entity;
    }
}
