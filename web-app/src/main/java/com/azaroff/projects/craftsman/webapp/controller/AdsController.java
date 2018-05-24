package com.azaroff.projects.craftsman.webapp.controller;

import com.azaroff.projects.craftsman.annotation.AppRequaredToken;
import com.azaroff.projects.craftsman.ad.service.Ad;
import com.azaroff.projects.craftsman.ad.service.AdService;
import com.azaroff.projects.craftsman.customer.service.CustomerService;
import com.azaroff.projects.craftsman.exception.ControllerException;
import com.azaroff.projects.craftsman.exception.DAOException;
import com.azaroff.projects.craftsman.webapp.model.NewAdRequest;
import com.azaroff.projects.craftsman.webapp.model.constant.LoginStatus;
import com.azaroff.projects.craftsman.webapp.model.permission.Request;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.TokenIsExpiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
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
    private CustomerService customerService;

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
    @AppRequaredToken()
    public String getOwnAds(@RequestBody Request request) throws TokenIsExpiredException {
        try {
            List<Ad> allAds = adService.findByAuthor(request.getTokenAlias(), Base64.getDecoder().decode(request.getData()));
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

    @RequestMapping(value = "/checkOwner/{id}", method = RequestMethod.PUT)
    public String checkOwner(@PathVariable("id") int id, @RequestBody Request request) throws JsonProcessingException {
        String tokenAlias = request.getTokenAlias();
        byte[] data = Base64.getDecoder().decode(request.getData());
        return new ObjectMapper().writeValueAsString(customerService.checkOwnerForAd(id, data));
    }
}