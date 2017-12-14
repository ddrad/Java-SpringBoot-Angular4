package com.azaroff.projects.craftsman.ad.service;

import com.azaroff.projects.craftsman.exception.DAOException;

import java.util.List;

/**
 * Created by AzarovD on 25.08.2016.
 */


public interface AdService {

    Ad saveAd(String tokenAlias, Ad ad) throws DAOException;

    Ad findById(int id);

    Ad findById(String id);

    List<Ad> findAllAds();

    List<Ad> findByAuthor(String tokenAlias);

    void updateAd(Ad ad);

}
