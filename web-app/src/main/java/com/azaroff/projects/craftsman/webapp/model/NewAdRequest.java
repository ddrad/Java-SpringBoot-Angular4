package com.azaroff.projects.craftsman.webapp.model;

import com.azaroff.projects.craftsman.ad.service.Ad;
import com.azaroff.projects.craftsman.webapp.model.permission.BaseRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Copyright DonRiver Inc. All Rights Reserved.
 * <p>
 * Author: Dmitry Azarov
 * Created: 05.12.2017.
 */
public class NewAdRequest extends BaseRequest {
    Ad ad;

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

}
