package com.example.two_tables;

public class cartModel {

    String name;
    String price;
    byte[] poster;

    public cartModel(String name, String price, byte[] poster) {
        this.name = name;
        this.price = price;
        this.poster = poster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public byte[] getPoster() {
        return poster;
    }

    public void setPoster(byte[] poster) {
        this.poster = poster;
    }
}
