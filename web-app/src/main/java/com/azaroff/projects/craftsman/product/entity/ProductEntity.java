package com.azaroff.projects.craftsman.product.entity;

import com.azaroff.projects.craftsman.ad.service.Ad;

import javax.persistence.*;

/**
 * Created by user on 12.10.2017.
 */
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "cost", nullable = false)
    private int cost;

//    @ManyToOne
//    @JoinColumn(name="ad_id", nullable=false)
//    private Ad ad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

//    public Ad getAd() {
//        return ad;
//    }
//
//    public void setAd(Ad ad) {
//        this.ad = ad;
//    }
}
