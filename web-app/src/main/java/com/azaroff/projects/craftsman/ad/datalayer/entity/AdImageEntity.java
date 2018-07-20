package com.azaroff.projects.craftsman.ad.datalayer.entity;

import javax.persistence.*;

@Entity
@Table(name="ad_image")
public class AdImageEntity {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "img_id_seq", sequenceName = "img_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "img_id_seq")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Lob
    @Column(name="img")
    private byte[] img;

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
