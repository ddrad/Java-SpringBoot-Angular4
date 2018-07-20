package com.azaroff.projects.craftsman.ad.service.convert;

import com.azaroff.projects.craftsman.ad.datalayer.entity.AdEntity;
import com.azaroff.projects.craftsman.ad.datalayer.entity.AdImageEntity;
import com.azaroff.projects.craftsman.ad.service.Ad;
import com.azaroff.projects.craftsman.ad.service.AdImage;
import com.azaroff.projects.craftsman.ad.service.AdInfo;
import com.azaroff.projects.craftsman.product.service.convert.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

        List<AdImageEntity> img = new ArrayList<>();
        AdImageEntity i = new AdImageEntity();
        i.setImg(ad.getImageFile().getValue().getBytes());
        i.setName(ad.getImageFile().getFilename());
        i.setType(ad.getImageFile().getFiletype());
        img.add(i);
        entity.setImages(img);

        return entity;
    }

    public Ad toAd(AdEntity entity) {
        Ad customer = new Ad();
        customer.setId(entity.getId());
        customer.setTitle(entity.getTitle());
        customer.setDescription(entity.getDescription());
      //  customer.setImagePath(entity.getImagePath());
        customer.setAuthor(entity.getAuthor());
        customer.setProducts(productConverter.toProduct(entity.getProducts()));

        customer.setImageFile(entity.getImages().stream().map(i ->
        {
            AdImage img= new AdImage();
            img.setFilename(i.getName());
            img.setFiletype(i.getType());
            img.setValue(Base64.getEncoder().encodeToString(i.getImg()));
            customer.setImagePath("'data:image/png;base64,'" + img.getValue());
            return img;
        }).collect(Collectors.toList()).get(0));



        return customer;
    }

}
