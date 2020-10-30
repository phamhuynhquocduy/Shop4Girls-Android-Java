package com.example.shop4girls.model;

import java.io.Serializable;
import java.util.Comparator;

public class Product implements Serializable {
    public int id;
    public String name;
    public Integer price;
    public String image;
    public String description;
    public int idCategory;
    public int firm;

    public Product(int id, String name, Integer price, String image, String description, int idCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.idCategory = idCategory;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getFirm() {
        return firm;
    }

    public void setFirm(int firm) {
        this.firm = firm;
    }

    public static Comparator<Product> ascendingPrice = new Comparator<Product>() {

        public int compare(Product p1, Product p2) {

            int product1 = p1.getPrice();
            int product2 = p2.getPrice();

            return product1-product2;
        }};
    public static Comparator<Product> decreasePrice = new Comparator<Product>() {

        public int compare(Product p1, Product p2) {

            int product1 = p1.getPrice();
            int product2 = p2.getPrice();

            return product2-product1;
        }};
    public static Comparator<Product> nameAtoZ = new Comparator<Product>() {

        public int compare(Product p1, Product p2) {
            String  product1 = p1.getName().toUpperCase();
            String  product2 = p2.getName().toUpperCase();

            return product1.compareTo(product2);
        }};
    public static Comparator<Product> nameZtoA = new Comparator<Product>() {

        public int compare(Product p1, Product p2) {
            String product1 = p1.getName().toUpperCase();
            String product2 = p2.getName().toUpperCase();

            return product2.compareTo(product1);
        }};
}
