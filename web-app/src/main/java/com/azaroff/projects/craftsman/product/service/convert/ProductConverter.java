package com.azaroff.projects.craftsman.product.service.convert;

import com.azaroff.projects.craftsman.product.entity.ProductEntity;
import com.azaroff.projects.craftsman.product.service.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 12.10.2017.
 */
@Service("productConverter")
public class ProductConverter {

    public Product toProduct(ProductEntity entity) {
        Product product = new Product();
        product.setId(entity.getId());
        product.setCost(entity.getCost());
        product.setProductName(entity.getProductName());
//        product.setAdId(entity.getAdId());
        return product;
    }

    public ProductEntity toProductEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setCost(product.getCost());
        entity.setProductName(product.getProductName());
//        entity.setAdId(product.getAdId());
        return entity;
    }

    public List<ProductEntity> toProductEntity(List<Product> products) {
        List<ProductEntity> entities = new ArrayList<>();
        for (Product product : products){
            entities.add(toProductEntity(product));
        }
        return entities;
    }

    public List<Product> toProduct(List<ProductEntity> productEntities) {
        List<Product> products = new ArrayList<>();
        for (ProductEntity entity : productEntities){
            products.add(toProduct(entity));
        }
        return products;
    }
}

