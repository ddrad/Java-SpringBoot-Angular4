package com.azaroff.projects.craftsman.token.service;

import org.joda.time.DateTime;

/**
 * Created by AzarovD on 24.08.2016.
 */

public class TokenData {

    private int tokenId;
    private String alias;
    private DateTime expirationTime;
    private boolean removeAfterExpiration;
    private TokenDataStatus status;
    private Object data;

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public DateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(DateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public boolean isRemoveAfterExpiration() {
        return removeAfterExpiration;
    }

    public void setRemoveAfterExpiration(boolean removeAfterExpiration) {
        this.removeAfterExpiration = removeAfterExpiration;
    }

    public TokenDataStatus getStatus() {
        return status;
    }

    public void setStatus(TokenDataStatus status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenData tokenData = (TokenData) o;

        if (tokenId != tokenData.tokenId) return false;
        if (removeAfterExpiration != tokenData.removeAfterExpiration) return false;
        if (alias != null ? !alias.equals(tokenData.alias) : tokenData.alias != null) return false;
        if (expirationTime != null ? !expirationTime.equals(tokenData.expirationTime) : tokenData.expirationTime != null)
            return false;
        return status != null ? status.equals(tokenData.status) : tokenData.status == null;

    }

    @Override
    public int hashCode() {
        int result = tokenId;
        result = 31 * result + (alias != null ? alias.hashCode() : 0);
        result = 31 * result + (expirationTime != null ? expirationTime.hashCode() : 0);
        result = 31 * result + (removeAfterExpiration ? 1 : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
