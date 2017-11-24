package com.azaroff.projects.craftsman.ad.service;

import java.util.List;

/**
 * Created by AzarovD on 25.08.2016.
 */


public interface AdService {

    Ad saveAd(Ad ad);

    Ad findById(int id);

    Ad findById(String id);

    List<AdInfo> findAllAds();

    List<AdInfo> findByAuthor(int authorId);

    void updateAd(Ad ad);

}
