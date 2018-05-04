package com.azaroff.projects.craftsman.ad.service.impl;

import com.azaroff.projects.craftsman.ad.datalayer.dao.AdRepository;
import com.azaroff.projects.craftsman.ad.datalayer.entity.AdEntity;
import com.azaroff.projects.craftsman.ad.service.Ad;
import com.azaroff.projects.craftsman.ad.service.AdService;
import com.azaroff.projects.craftsman.ad.service.convert.AdConvert;
import com.azaroff.projects.craftsman.customer.service.Customer;
import com.azaroff.projects.craftsman.exception.DAOException;
import com.azaroff.projects.craftsman.token.service.TokenData;
import com.azaroff.projects.craftsman.token.service.TokenService;
import com.azaroff.projects.craftsman.webapp.model.constant.LoginStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by AzarovD on 25.08.2016.
 */

@Service("adService")
public class AdServiceImpl implements AdService {

    @Autowired
    private AdRepository repository;
    @Autowired
    @Qualifier("adConvert")
    private AdConvert convert;
    @Autowired
    @Qualifier("tokenService")
    private TokenService tokenService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Ad saveAd(String tokenAlias, Ad ad) throws DAOException {
        TokenData tokenData = tokenService.findByAlias(tokenAlias);
        if (tokenData == null) {
            throw new DAOException(LoginStatus.FAIL, "Token data was not found");
        }
        if (tokenService.isExpiredTokenData(tokenData)) {
            throw new DAOException(LoginStatus.EXPIRED, "Token was expired");
        }
        Customer customer = (Customer) tokenData.getData();
        ad.setAuthor(customer.getId());
        AdEntity entity = repository.save(convert.toAdEntity(ad));
        return convert.toAd(entity);
    }

    @Override
    @Transactional
    public Ad findById(int id) {
        Optional<AdEntity> entities = repository.findById(id);
        if (entities == null) {
            return null;
        }
        return convert.toAd(entities.get());
    }

    @Override
    @Transactional
    public Ad findById(String id) {
        int intId = Integer.parseInt(id);
        return findById(intId);
    }

    @Override
    @Transactional
    public List<Ad> findAllAds() {
        Iterable<AdEntity> entities = repository.findAll();
        List<Ad> ads = new ArrayList<>();
        for (AdEntity entity : entities) {
            ads.add(convert.toAd(entity));
        }
        return ads;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Ad> findByAuthor(String tokenAlias, byte[] tokenData) throws DAOException {
        //TODO: must verify token data
        Customer customer = (Customer) tokenService.deserialize(tokenData).getDesirializedObject();
        Iterable<AdEntity> entities = repository.findByAuthor(customer.getId());
        return StreamSupport.stream(entities.spliterator(), false)
                .map((d)-> convert.toAd(d)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateAd(Ad ad) {

    }

}
