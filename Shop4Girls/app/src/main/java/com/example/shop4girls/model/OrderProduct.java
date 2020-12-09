package com.example.shop4girls.model;

import android.util.EventLogTags;

import java.io.Serializable;

public class OrderProduct implements Serializable {
    private int id;
    private   String name;
    private String image;
    private Integer price;
    private int count;
    private Integer total;
    private int status;
    private String description;
    double rating;
    int category;

    public OrderProduct(int id, String name, String image, Integer price, int count, Integer total, int status, String description, double rating, int category) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.count = count;
        this.total = total;
        this.status = status;
        this.description = description;
        this.rating = rating;
        this.category = category;
    }

    public OrderProduct(int id, String name, String image, Integer price, int count, Integer total, int status) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.count = count;
        this.total = total;
        this.status = status;
    }

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


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }


}
