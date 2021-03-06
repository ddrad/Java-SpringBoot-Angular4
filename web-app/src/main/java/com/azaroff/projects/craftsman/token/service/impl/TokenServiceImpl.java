package com.azaroff.projects.craftsman.token.service.impl;

import com.azaroff.projects.craftsman.token.datalayer.dao.TokenRepository;
import com.azaroff.projects.craftsman.token.datalayer.entity.TokenDataEntity;
import com.azaroff.projects.craftsman.token.service.DesirializeWrapper;
import com.azaroff.projects.craftsman.token.service.TokenData;
import com.azaroff.projects.craftsman.token.service.TokenDataStatus;
import com.azaroff.projects.craftsman.token.service.TokenService;
import com.azaroff.projects.craftsman.token.service.convert.TokenDataConverter;
import com.azaroff.projects.craftsman.token.service.error.Error;
import com.azaroff.projects.craftsman.token.service.util.KryoSerializer;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * Created by AzarovD on 24.08.2016.
 */

@Service("tokenService")
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    @Qualifier("tokenDataConverter")
    private TokenDataConverter tokenDataConverter;
    @Autowired
    @Qualifier("kryoSerializer")
    private KryoSerializer serializer;

    @Override
    @Transactional
    public TokenData generateToken(Object data, DateTime dt, boolean removeAfterExpiration, String... aliasData) {
        if (StringUtils.isEmpty(aliasData)) {
            throw new RuntimeException(Error.ALIAS_IS_NULL.name());
        }
        // generate unique tokenAlias and encode to base64
        byte[] tokenAlias = tokenDataConverter.generateTokenAlias(aliasData);
        String strTokenAlias = Base64.getEncoder().encodeToString(tokenAlias);
        //Finf token data by this token
        TokenDataEntity tokenDataEntity = tokenRepository.findByAlias(strTokenAlias);

        if (tokenDataEntity != null) {
            TokenDataEntity entity = tokenRepository.save(updateParamsAndExpirationTime(tokenDataEntity, data, dt));
            return tokenDataConverter.convertFromEntity(entity);
        } else {
            TokenDataEntity entity = tokenRepository.save(createTokenDataEntity(strTokenAlias, data, dt, removeAfterExpiration));
            return tokenDataConverter.convertFromEntity(entity);
        }
    }

    private TokenDataEntity createTokenDataEntity(String alias, Object data, DateTime dt, boolean removeAfterExpiration) {
        TokenData tokenData = new TokenData();
        tokenData.setAlias(alias);
        tokenData.setData(data);
        tokenData.setExpirationTime(dt.plusMinutes(30));
        tokenData.setStatus(TokenDataStatus.ACTIVE);
        tokenData.setRemoveAfterExpiration(removeAfterExpiration);
        return tokenDataConverter.convertToEntity(tokenData);
    }

    private TokenDataEntity updateParamsAndExpirationTime(TokenDataEntity entity, Object data, DateTime dt) {
        // plus 30 minutes for expired date
        Date date = dt.plusMinutes(30).toDate();
        entity.setExpirationTime(date);
        entity.setData(serializer.serialize(data));
        return entity;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public TokenData getTokenDataById(int tokenId) {
        TokenDataEntity entity = tokenRepository.findById(tokenId).get();
        if (entity == null) {
            throw new RuntimeException(Error.TOKEN_DATA_IS_NOT_FOUND.name());
        }
        return tokenDataConverter.convertFromEntity(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public TokenData findByAlias(String alias) {
        TokenDataEntity tokenDataEntity = tokenRepository.findByAlias(alias);
        if (tokenDataEntity == null) {
            return null;
        }
        return tokenDataConverter.convertFromEntity(tokenDataEntity);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void removeByAlias(String alias) {
        if (StringUtils.isEmpty(alias)) {
            return;
        }
        tokenRepository.deleteByAlias(alias);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void removeById(int id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        tokenRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void removeExpiredTokenData() {
        tokenRepository.removeExpiredTokenData();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void updateTokenStatus(List<Integer> tokenIds, TokenDataStatus tokenStatus) {
        if (tokenIds.isEmpty()) {
            return;
        }
        tokenRepository.updateTokenDataEntities(tokenIds, tokenStatus);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void updateToken(TokenData tokenData) {
        TokenDataEntity entity = tokenRepository.findById(tokenData.getTokenId()).get();
        if (entity == null) {
            throw new RuntimeException(Error.TOKEN_DATA_IS_NOT_FOUND.name());
        }
        tokenDataConverter.mergeTokenEntity(entity, tokenData);
        tokenRepository.save(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean isExpiredTokenData(TokenData tokenData) {
        DateTime expirationTime = tokenData.getExpirationTime();
        return expirationTime.isBeforeNow();
    }

    @Override
    public DesirializeWrapper deserialize(byte[] data) {
        return () -> serializer.deserialize(data);
    }
}
