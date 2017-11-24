package com.azaroff.projects.craftsman.product.service;

/**
 * Created by user on 10.10.2017.
 */
public class Product {
    private int id;
    private String productName;
    private int cost;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
