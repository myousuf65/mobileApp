package com.example.two_tables;


public class moviesModel {

    private String name;
    private String description;
    private String price;
    private String video_id;
    private byte[] poster;

    public moviesModel(){}

    public moviesModel( String name, String price, String description, String video_id, byte[] poster) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.video_id = video_id;
        this.poster = poster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public byte[] getPoster() {
        return poster;
    }

    public void setPoster(byte[] poster) {
        this.poster = poster;
    }
}
