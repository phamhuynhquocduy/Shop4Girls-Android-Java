package com.example.shop4girls.model;

public class Cart {
    private int id;
    private String name;
    private long pirce;
    private String image;
    private int count;

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

    public long getPirce() {
        return pirce;
    }

    public void setPirce(long pirce) {
        this.pirce = pirce;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Cart(int id, String name, long pirce, String image, int count) {
        this.id = id;
        this.name = name;
        this.pirce = pirce;
        this.image = image;
        this.count = count;
    }
}
