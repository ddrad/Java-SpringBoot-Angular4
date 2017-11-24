package com.azaroff.projects.craftsman.ad.service;

import com.azaroff.projects.craftsman.product.service.Product;

/**
 * Created by AzarovD on 25.08.2016.
 */

public class AdInfo {

    private int id;
    private String title;
    private String description;
    private String imagePath;
    private int author;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdInfo adInfo = (AdInfo) o;

        if (id != adInfo.id) return false;
        if (author != adInfo.author) return false;
        if (title != null ? !title.equals(adInfo.title) : adInfo.title != null) return false;
        if (description != null ? !description.equals(adInfo.description) : adInfo.description != null) return false;
        return imagePath != null ? imagePath.equals(adInfo.imagePath) : adInfo.imagePath == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = 31 * result + author;
        return result;
    }
}
