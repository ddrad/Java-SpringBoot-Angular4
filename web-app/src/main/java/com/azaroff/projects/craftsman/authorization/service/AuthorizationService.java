package com.azaroff.projects.craftsman.authorization.service;

/**
 * Created by Dmitiy on 27.08.2016.
 */
public interface AuthorizationService {

    Authorization findByAuthID(String authorizationId);

    Authorization findByAuthData(String login, String password);

    Authorization addAuthorizationData(Authorization authorization);

}
