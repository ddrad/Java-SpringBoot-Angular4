package com.azaroff.projects.craftsman.token.service;

import org.joda.time.DateTime;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

/**
 * Created by AzarovD on 24.08.2016.
 */

public interface TokenService {

    TokenData generateToken(String alias, Object data, DateTime dt, boolean removeAfterExpiration);

    TokenData getTokenDataById(int tokenId);

    TokenData findByAlias(String alias);

    void removeByAlias(String alias);

    void removeById(int id);

    void removeExpiredTokenData();

    void updateTokenStatus(List<Integer> tokenIds, TokenDataStatus tokenStatus);

    void updateToken(TokenData tokenData);

    boolean isExpiredTokenData(TokenData tokenData);
}
