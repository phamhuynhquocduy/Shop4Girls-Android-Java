package com.example.shop4girls.model;

import java.io.Serializable;

public class OrderProduct implements Serializable {
    private int id;
    private   String name;
    private String image;
    private Integer price;
    private int count;
    private Integer total;

    public OrderProduct(int id, String name, String image, Integer price, int count, Integer total) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.count = count;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public OrderProduct(String name, String image, Integer price, int count, Integer total) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.count = count;
        this.total = total;
    }


}
