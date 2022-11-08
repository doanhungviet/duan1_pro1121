package com.example.duan1_baove.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duan1_baove.fragment.hocvien.CaNhan_Fragment_HocVien;
import com.example.duan1_baove.fragment.hocvien.CuaHang_Fragment_HocVien;
import com.example.duan1_baove.fragment.hocvien.TrangChu_Fragment_HocVien;

public class AdapterBotTonNav_HocVien extends FragmentStateAdapter {

    public AdapterBotTonNav_HocVien(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1: return new CuaHang_Fragment_HocVien();
            case 2: return new CaNhan_Fragment_HocVien();
            default: return new TrangChu_Fragment_HocVien();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
