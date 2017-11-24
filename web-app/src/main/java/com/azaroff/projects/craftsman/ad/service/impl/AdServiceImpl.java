package com.azaroff.projects.craftsman.ad.service.impl;

import com.azaroff.projects.craftsman.ad.datalayer.dao.AdRepository;
import com.azaroff.projects.craftsman.ad.datalayer.entity.AdEntity;
import com.azaroff.projects.craftsman.ad.service.Ad;
import com.azaroff.projects.craftsman.ad.service.AdInfo;
import com.azaroff.projects.craftsman.ad.service.AdService;
import com.azaroff.projects.craftsman.ad.service.convert.AdConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    @Transactional
    public Ad saveAd(Ad ad) {
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
    public List<AdInfo> findAllAds() {
        Iterable<AdEntity> entities = repository.findAll();
        List<AdInfo> ads = new ArrayList<AdInfo>();
        for (AdEntity entity : entities) {
            ads.add(convert.toAdInfo(entity));
        }
        return ads;
    }

    @Override
    public List<AdInfo> findByAuthor(int authorId) {
        Iterable<AdEntity> entities = repository.findByAuthor(authorId);
        List<AdInfo> ads = new ArrayList<AdInfo>();
        for (AdEntity entity : entities) {
            ads.add(convert.toAdInfo(entity));
        }
        return ads;
    }

    @Override
    @Transactional
    public void updateAd(Ad ad) {

    }

}
