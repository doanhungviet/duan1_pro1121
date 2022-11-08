package com.example.duan1_baove.model;

public class ViTri {
    private int img;
    private String name;

    public ViTri(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public ViTri() {
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
