package com.azaroff.projects.craftsman.webapp.controller;

import com.azaroff.projects.craftsman.ad.service.Ad;
import com.azaroff.projects.craftsman.ad.service.AdInfo;
import com.azaroff.projects.craftsman.ad.service.AdService;
import com.azaroff.projects.craftsman.customer.service.Customer;
import com.azaroff.projects.craftsman.token.service.TokenData;
import com.azaroff.projects.craftsman.token.service.TokenService;
import com.azaroff.projects.craftsman.webapp.model.permission.BaseRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
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
    @Autowired
    @Qualifier("tokenService")
    private TokenService tokenService;

    @RequestMapping("/all")
    public String getAllAds() throws JsonProcessingException {
        List<AdInfo> allAds = adService.findAllAds();
        return new ObjectMapper().writeValueAsString(allAds);
    }

    @RequestMapping("/own")
    public String getOwnAds(@RequestBody BaseRequest request) throws JsonProcessingException {
        TokenData tokenData = tokenService.findByAlias(request.getTokenAlias());
        if(null == tokenData) {

        }
        Customer customer = (Customer) tokenData.getData();
        List<AdInfo> allAds = adService.findByAuthor(customer.getId());
        return new ObjectMapper().writeValueAsString(allAds);
    }

    @RequestMapping(value = "/ad/{id}", method = RequestMethod.GET)
    public String fetchAdById(@PathVariable("id") int id) throws JsonProcessingException {
        Ad ad = adService.findById(id);
        return new ObjectMapper().writeValueAsString(ad);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody Ad ad) throws JsonProcessingException  {
        Ad savedAd = adService.saveAd(ad);
        return new ObjectMapper().writeValueAsString(savedAd);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Ad ad) {
        adService.saveAd(ad);
    }
}
