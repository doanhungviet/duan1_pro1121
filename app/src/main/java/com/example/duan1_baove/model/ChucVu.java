package com.example.duan1_baove.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "chucvu")
public class ChucVu {

    @PrimaryKey (autoGenerate = true)
    private int id;
    private String tenchucvu;

    public ChucVu(int id, String tenchucvu) {
        this.id = id;
        this.tenchucvu = tenchucvu;
    }

    public ChucVu() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenchucvu() {
        return tenchucvu;
    }

    public void setTenchucvu(String tenchucvu) {
        this.tenchucvu = tenchucvu;
    }
}
