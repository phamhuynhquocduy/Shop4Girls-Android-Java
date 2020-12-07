package com.example.shop4girls.model;

public class Firm {
    int id;
    String Name;

    @Override
    public String toString() {
        return Name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Firm(int id, String name) {
        this.id = id;
        Name = name;
    }
}
