package com.azaroff.projects.craftsman.ad.service.convert;

import com.azaroff.projects.craftsman.ad.datalayer.entity.AdEntity;
import com.azaroff.projects.craftsman.ad.service.Ad;
import com.azaroff.projects.craftsman.ad.service.AdInfo;
import com.azaroff.projects.craftsman.product.service.convert.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by AzarovD on 25.08.2016.
 */

@Service("adConvert")
public class AdConvert {

    @Autowired
    @Qualifier("productConverter")
    private ProductConverter productConverter;

    public AdEntity toAdEntity(AdInfo ad) {
        AdEntity entity = new AdEntity();
        entity.setId(ad.getId());
        entity.setTitle(ad.getTitle());
        entity.setDescription(ad.getDescription());
        entity.setImagePath(ad.getImagePath());
        entity.setAuthor(ad.getAuthor());
        return entity;
    }


    public AdEntity toAdEntity(Ad ad) {
        AdEntity entity = new AdEntity();
        entity.setId(ad.getId());
        entity.setTitle(ad.getTitle());
        entity.setDescription(ad.getDescription());
        entity.setImagePath(ad.getImagePath());
        entity.setAuthor(ad.getAuthor());
        entity.setProducts(productConverter.toProductEntity(ad.getProducts()));
        return entity;
    }

    public AdInfo toAdInfo(AdEntity entity) {
        AdInfo customer = new AdInfo();
        customer.setId(entity.getId());
        customer.setTitle(entity.getTitle());
        customer.setDescription(entity.getDescription());
        customer.setImagePath(entity.getImagePath());
        customer.setAuthor(entity.getAuthor());
        return customer;
    }

    public Ad toAd(AdEntity entity) {
        Ad customer = new Ad();
        customer.setId(entity.getId());
        customer.setTitle(entity.getTitle());
        customer.setDescription(entity.getDescription());
        customer.setImagePath(entity.getImagePath());
        customer.setAuthor(entity.getAuthor());
        customer.setProducts(productConverter.toProduct(entity.getProducts()));
        return customer;
    }

}
