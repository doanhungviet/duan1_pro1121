package com.example.duan1_baove.fragment.admin;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_baove.R;
import com.example.duan1_baove.adapter.LoaiTheTapAdapter;
import com.example.duan1_baove.database.DuAn1DataBase;
import com.example.duan1_baove.model.LoaiTheTap;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class LoaiTheTap_Fragment_Admin extends Fragment {
    View view;
    RecyclerView recyclerView;
    LinearLayout layout_search;
    EditText edt_search;
    FloatingActionButton fab1,fab2,fab3;
    Animation fab_open,fab_close,rotateForward,rotateBackward;
    boolean isOpen;

    EditText edt_maloaithe,edt_tenloaithe,edt_gialoaithe,edt_hansudung;
    Button btn_add,btn_huy;
    LoaiTheTap loaiTheTap;

    LoaiTheTapAdapter adapter;
    List<LoaiTheTap> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_loai_the_tap___admin, container, false);
        initUi();
        capnhat();
        fab1.setOnClickListener(v -> {
            animateFab();
        });
        fab2.setOnClickListener(v -> {
            layout_search.setVisibility(View.VISIBLE);
            animateFab();
        });
        fab3.setOnClickListener(v -> {
            add();
            animateFab();
        });
        edt_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    search(edt_search.getText().toString().trim());
                    hideSoftKeyBroad();
                }
                return false;
            }
        });
        return view;
    }

    private void initUi() {
        recyclerView = view.findViewById(R.id.rcy_loaithetap);
        fab1 = view.findViewById(R.id.fab1_loaithetap);
        fab2 = view.findViewById(R.id.fab2_loaithetap);
        fab3 = view.findViewById(R.id.fab3_loaithetap);
        fab_open = AnimationUtils.loadAnimation(getContext(),R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getContext(),R.anim.fab_close);
        rotateForward = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_backward);
        layout_search = view.findViewById(R.id.layout_search_loaithetap);
        edt_search = view.findViewById(R.id.edt_search_loaithetap);
    }
    private void search(String search) {
        adapter.getFilter().filter(search);
    }

    private void animateFab() {
        if (isOpen){
            fab1.startAnimation(rotateForward);
            fab2.startAnimation(fab_close);
            fab3.startAnimation(fab_close);
            fab2.setClickable(false);
            fab3.setClickable(false);
            fab1.setImageResource(R.drawable.ic_menu);
            isOpen = false;
        }else {
            fab1.startAnimation(rotateBackward);
            fab2.startAnimation(fab_open);
            fab3.startAnimation(fab_open);
            fab2.setClickable(true);
            fab3.setClickable(true);
            fab1.setImageResource(R.drawable.ic_close);
            isOpen = true;
        }
    }
    private void add() {
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_addloaithetap);
        dialog.show();
        Window window = dialog.getWindow();
        if (window == null){
            return;
        }
        window.setBackgroundDrawable(null);
        edt_maloaithe = dialog.findViewById(R.id.edt_maloaithetap_dialogloaithetap);
        edt_tenloaithe = dialog.findViewById(R.id.edt_tenthetap_dialogloaithetap);
        edt_gialoaithe = dialog.findViewById(R.id.edt_giathetap_dialogloaithetap);
        edt_hansudung = dialog.findViewById(R.id.edt_hansudung_dialogloaithetap);
        btn_add = dialog.findViewById(R.id.btn_luu_dialogloaithetap);
        btn_huy = dialog.findViewById(R.id.btn_huy_dialogloaithetap);

        btn_huy.setOnClickListener(v -> {
            dialog.cancel();
        });
        btn_add.setOnClickListener(v -> {
            if (validate()){
                loaiTheTap = new LoaiTheTap();
                loaiTheTap.setName(edt_tenloaithe.getText().toString().trim());
                loaiTheTap.setGia(Integer.parseInt(edt_gialoaithe.getText().toString()));
                loaiTheTap.setHanSuDung(edt_hansudung.getText().toString().trim());
                DuAn1DataBase.getInstance(getContext()).loaiTheTapDAO().insert(loaiTheTap);
                Toast.makeText(getContext(), "Insert loại thẻ tập thành công", Toast.LENGTH_SHORT).show();
                capnhat();
                dialog.cancel();
            }
        });
    }

    private void capnhat() {
        list = DuAn1DataBase.getInstance(getContext()).loaiTheTapDAO().getAll();
        adapter = new LoaiTheTapAdapter(getContext());
        adapter.setData(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }


    private boolean validate(){
        if (edt_tenloaithe.getText().toString().isEmpty() || edt_gialoaithe.getText().toString().isEmpty() || edt_hansudung.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Vui lòng không bỏ trống thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            try {
                Integer.parseInt(edt_gialoaithe.getText().toString());
                Integer.parseInt(edt_hansudung.getText().toString());
                return true;
            }catch (Exception e){
                Toast.makeText(getContext(), "Giá và hạn sử dụng phải là số", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }
    public void hideSoftKeyBroad(){
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),0);
    }
}