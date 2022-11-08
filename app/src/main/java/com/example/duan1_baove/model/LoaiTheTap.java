package com.example.duan1_baove.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "loaithetap")
public class LoaiTheTap {

    @PrimaryKey (autoGenerate = true)
    private int id;
    private String name;
    private int gia;
    private String hanSuDung;

    public LoaiTheTap(int id, String name, int gia, String hanSuDung) {
        this.id = id;
        this.name = name;
        this.gia = gia;
        this.hanSuDung = hanSuDung;
    }

    public LoaiTheTap() {
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

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getHanSuDung() {
        return hanSuDung;
    }

    public void setHanSuDung(String hanSuDung) {
        this.hanSuDung = hanSuDung;
    }
}
