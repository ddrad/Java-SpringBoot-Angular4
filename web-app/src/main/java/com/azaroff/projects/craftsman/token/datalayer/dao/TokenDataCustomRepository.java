package com.azaroff.projects.craftsman.token.datalayer.dao;

/**
 * Created by AzarovD on 24.08.2016.
 */

import com.azaroff.projects.craftsman.token.datalayer.entity.TokenDataEntity;
import com.azaroff.projects.craftsman.token.service.TokenDataStatus;

import java.util.List;

public interface TokenDataCustomRepository {

    void updateTokenDataEntities(List<Integer> tokenDataIdList, TokenDataStatus tokenStatus);

    void removeExpiredTokenData();

}