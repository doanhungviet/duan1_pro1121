package com.example.duan1_baove.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.android.material.tabs.TabLayout;

@Entity (tableName = "donhangchitiet", foreignKeys = {
        @ForeignKey(entity = CuaHang.class,
                parentColumns = "id",
                childColumns = "cuahang_id",onDelete = CASCADE,onUpdate = CASCADE),
        @ForeignKey(entity = TheTap.class,
                parentColumns = "id",
                childColumns = "thetap_id",onDelete = CASCADE,onUpdate = CASCADE)})
public class DonHangChiTiet {
    @PrimaryKey (autoGenerate = true)
    private int id;
    private int cuahang_id;
    private int thetap_id;
    private int soLuong;
    private String starttime;
    private String endtime;

    public DonHangChiTiet(int id, int cuahang_id, int thetap_id, int soLuong, String starttime, String endtime) {
        this.id = id;
        this.cuahang_id = cuahang_id;
        this.thetap_id = thetap_id;
        this.soLuong = soLuong;
        this.starttime = starttime;
        this.endtime = endtime;
    }

    public DonHangChiTiet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCuahang_id() {
        return cuahang_id;
    }

    public void setCuahang_id(int cuahang_id) {
        this.cuahang_id = cuahang_id;
    }

    public int getThetap_id() {
        return thetap_id;
    }

    public void setThetap_id(int thetap_id) {
        this.thetap_id = thetap_id;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
