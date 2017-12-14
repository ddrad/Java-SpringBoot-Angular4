package com.azaroff.projects.craftsman.webapp.controller;

import com.azaroff.projects.craftsman.ad.service.Ad;
import com.azaroff.projects.craftsman.ad.service.AdService;
import com.azaroff.projects.craftsman.exception.ControllerException;
import com.azaroff.projects.craftsman.exception.DAOException;
import com.azaroff.projects.craftsman.webapp.model.NewAdRequest;
import com.azaroff.projects.craftsman.webapp.model.constant.LoginStatus;
import com.azaroff.projects.craftsman.webapp.model.permission.BaseRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by user on 09.10.2017.
 */

@RestController
@RequestMapping("/app-ads")
public class AdsController {

    @Autowired
    @Qualifier("adService")
    private AdService adService;

    @RequestMapping("/all")
    public String getAllAds() {
        try {
            List<Ad> allAds = adService.findAllAds();
            return new ObjectMapper().writeValueAsString(allAds);
        } catch (JsonProcessingException e) {
            throw new ControllerException(LoginStatus.SYSTEM_UNPREDICTABLE_ERROR, e.getMessage());
        }
    }

    @RequestMapping("/own")
    public String getOwnAds(@RequestBody BaseRequest request) {
        try {
            List<Ad> allAds = adService.findByAuthor(request.getTokenAlias());
            return new ObjectMapper().writeValueAsString(allAds);
        } catch (JsonProcessingException e) {
            throw new ControllerException(LoginStatus.SYSTEM_UNPREDICTABLE_ERROR, e.getMessage());
        }
    }

    @RequestMapping(value = "/ad/{id}", method = RequestMethod.GET)
    public String fetchAdById(@PathVariable("id") int id) {
        try {
            Ad ad = adService.findById(id);
            return new ObjectMapper().writeValueAsString(ad);
        } catch (JsonProcessingException e) {
            throw new ControllerException(LoginStatus.SYSTEM_UNPREDICTABLE_ERROR, e.getMessage());
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody NewAdRequest newAdRequest) {
        String tokenAlias = newAdRequest.getTokenAlias();
        try {
            Ad ad = newAdRequest.getAd();
            Ad savedAd = adService.saveAd(tokenAlias, ad);
            return new ObjectMapper().writeValueAsString(savedAd);
        } catch (DAOException e) {
            throw new DAOException(e.getStatus(), e.getMessage());
        } catch (JsonProcessingException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Ad ad) {
        //adService.saveAd(ad);
    }
}
