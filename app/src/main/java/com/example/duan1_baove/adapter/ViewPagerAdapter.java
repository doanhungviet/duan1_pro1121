package com.example.duan1_baove.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duan1_baove.fragment.hocvien.Create_Fragment_HocVien;
import com.example.duan1_baove.fragment.hocvien.Login_Fragment_HocVien;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:return new Login_Fragment_HocVien();
            case 1:
                return new Create_Fragment_HocVien();
            default: return new Login_Fragment_HocVien();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
