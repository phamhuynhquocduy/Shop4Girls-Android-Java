package com.example.shop4girls.model;

public class Category {
    int id;
    String name;
    String image;
    int idParent;

    public Category(int id, String name, String image, int idParent) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.idParent = idParent;
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

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }
}
