package com.example.duan1_baove.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.duan1_baove.model.ChucVu;

import java.util.List;

@Dao
public interface ChucVuDAO {

    @Insert
    void insert(ChucVu chucVu);

    @Update
    void update(ChucVu chucVu);

    @Delete
    void delete(ChucVu chucVu);

    @Query("SELECT tenchucvu FROM chucvu WHERE id= :id")
    String chechChucVu(String id);

    @Query("SELECT * FROM chucvu WHERE id= :id")
    List<ChucVu> checkForId(String id);

    @Query("SELECT *FROM chucvu")
    List<ChucVu> getAll();
}
