package com.azaroff.projects.craftsman.ad.datalayer.entity;

/**
 * Created by AzarovD on 25.08.2016.
 */

import com.azaroff.projects.craftsman.product.entity.ProductEntity;
import com.azaroff.projects.craftsman.product.service.Product;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ad")
public class AdEntity {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "ad_id_seq", sequenceName = "ad_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ad_id_seq")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "image_path", nullable = false)
    private String imagePath;

    @Column(name = "author", nullable = false)
    private int author;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ad_id", referencedColumnName = "id")
    private List<ProductEntity> products;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}