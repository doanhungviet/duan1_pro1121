package com.example.duan1_baove.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.duan1_baove.model.KhachHang;

import java.util.List;

@Dao
public interface KhachHangDAO {

    @Insert
    void insert(KhachHang khachHang);

    @Query("SELECT *FROM khachhang WHERE soDienThoai= :sdt")
    List<KhachHang> checkAcc(String sdt);
}
