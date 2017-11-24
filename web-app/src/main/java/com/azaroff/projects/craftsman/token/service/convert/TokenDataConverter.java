package com.azaroff.projects.craftsman.token.service.convert;

/**
 * Created by AzarovD on 24.08.2016.
 */

import com.azaroff.projects.craftsman.token.datalayer.entity.TokenDataEntity;
import com.azaroff.projects.craftsman.token.service.TokenData;
import com.azaroff.projects.craftsman.token.service.util.KryoSerializer;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component("tokenDataConverter")
public class TokenDataConverter {

    @Autowired
    @Qualifier("kryoSerializer")
    private KryoSerializer serializer;

    public TokenDataEntity convertToEntity(TokenData tokenData) {
        TokenDataEntity entity = new TokenDataEntity();
        entity.setId(tokenData.getTokenId());
        entity.setData(serializer.serialize(tokenData.getData()));
        entity.setAlias(tokenData.getAlias());
        entity.setExpirationTime(tokenData.getExpirationTime().toDate());
        entity.setRemoveAfterExpiration(tokenData.isRemoveAfterExpiration());
        entity.setStatus(tokenData.getStatus());

        return entity;
    }

    public TokenData convertFromEntity(TokenDataEntity entity) {
        if (entity == null) {
            return null;
        }
        TokenData tokenData = new TokenData();
        tokenData.setTokenId(entity.getId());
        tokenData.setAlias(entity.getAlias());
        tokenData.setStatus(entity.getStatus());
        tokenData.setExpirationTime(new DateTime(entity.getExpirationTime()));
        tokenData.setData(serializer.deserialize(entity.getData()));
        tokenData.setRemoveAfterExpiration(entity.isRemoveAfterExpiration());
        return tokenData;
    }

    public List<TokenData> convertFromEntities(List<TokenDataEntity> tokenDataEntities) {
        if (tokenDataEntities.isEmpty()) {
            return Collections.emptyList();
        }
        List<TokenData> tokenDatas = new ArrayList<TokenData>(tokenDataEntities.size());
        for (TokenDataEntity tokenDataEntity : tokenDataEntities) {
            tokenDatas.add(convertFromEntity(tokenDataEntity));
        }
        return tokenDatas;
    }

    public List<TokenDataEntity> convertToEntities(List<TokenData> tokenData) {
        List<TokenDataEntity> tokenDataEntities = new ArrayList<TokenDataEntity>(tokenData.size());
        for (TokenData data : tokenData) {
            tokenDataEntities.add(convertToEntity(data));
        }
        return tokenDataEntities;
    }

    public void mergeTokenEntity(TokenDataEntity entity, TokenData tokenData) {
        entity.setAlias(tokenData.getAlias());
        entity.setData(serializer.serialize(tokenData.getData()));
        entity.setExpirationTime(tokenData.getExpirationTime().toDate());
        entity.setRemoveAfterExpiration(tokenData.isRemoveAfterExpiration());
        entity.setStatus(tokenData.getStatus());
    }
}
