package com.example.demo.model.entity;

import javax.persistence.*;
import java.sql.Blob;

@MappedSuperclass
public abstract class Product {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;

    Blob img;

    int price;

    public Product() {
    }

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idUser) {
        this.id = idUser;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Blob getImg() {
        return img;
    }

    public void setImg(Blob img) {
        this.img = img;
    }



}
