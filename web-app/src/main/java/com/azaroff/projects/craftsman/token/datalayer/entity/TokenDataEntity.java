package com.azaroff.projects.craftsman.token.datalayer.entity;

/**
 * Created by AzarovD on 24.08.2016.
 */

import com.azaroff.projects.craftsman.token.service.TokenDataStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "token_data")
public class TokenDataEntity {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "token_data_id_seq", sequenceName = "token_data_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_data_id_seq")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //TODO: Need will do  unique = true
    @Column(name = "alias")
    private String alias;

    @Column(name = "token_data")
    private byte[] data;

    @Column(name = "expiration_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationTime;

    @Column(name = "remove_after_expiration")
    boolean removeAfterExpiration;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TokenDataStatus status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Date getExpirationTime() {
        return new Date(expirationTime.getTime());
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = new Date(expirationTime.getTime());
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

}
