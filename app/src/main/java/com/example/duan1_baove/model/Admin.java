package com.example.duan1_baove.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "admin",foreignKeys = @ForeignKey(entity = ChucVu.class,parentColumns = "id",childColumns = "chucvu_id",onDelete = CASCADE,onUpdate = CASCADE))
public class Admin {

    @PrimaryKey
    @NonNull
    private String user;
    private String name;
    private String pass;
    private int chucvu_id;
    private int luong;
    private String hinhanh;
    private String stk;
    private String tennganhang;

    public Admin(@NonNull String user, String name, String pass, int chucvu_id, int luong, String hinhanh, String stk, String tennganhang) {
        this.user = user;
        this.name = name;
        this.pass = pass;
        this.chucvu_id = chucvu_id;
        this.luong = luong;
        this.hinhanh = hinhanh;
        this.stk = stk;
        this.tennganhang = tennganhang;
    }

    public Admin() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getChucvu_id() {
        return chucvu_id;
    }

    public void setChucvu_id(int chucvu_id) {
        this.chucvu_id = chucvu_id;
    }

    public int getLuong() {
        return luong;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getStk() {
        return stk;
    }

    public void setStk(String stk) {
        this.stk = stk;
    }

    public String getTennganhang() {
        return tennganhang;
    }

    public void setTennganhang(String tennganhang) {
        this.tennganhang = tennganhang;
    }
}
