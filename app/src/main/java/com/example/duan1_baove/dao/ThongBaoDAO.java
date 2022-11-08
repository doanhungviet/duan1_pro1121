package com.example.duan1_baove.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.duan1_baove.model.ThongBao;

import java.util.List;

@Dao
public interface ThongBaoDAO {

    @Insert
    void insert(ThongBao thongBao);

    @Update
    void update(ThongBao thongBao);

    @Query("SELECT *FROM thongbao ORDER BY id DESC")
    List<ThongBao> getAll();

    @Delete
    void delete(ThongBao thongBao);
}
