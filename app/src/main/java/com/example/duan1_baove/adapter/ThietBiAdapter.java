package com.example.duan1_baove.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_baove.R;
import com.example.duan1_baove.database.DuAn1DataBase;
import com.example.duan1_baove.model.Admin;
import com.example.duan1_baove.model.ThietBi;

import java.util.ArrayList;
import java.util.List;

public class ThietBiAdapter extends RecyclerView.Adapter<ThietBiAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<ThietBi> list;
    private List<ThietBi> listOld;

    EditText edt_id,edt_name,edt_loai,edt_soluong,edt_hangsanxuat;
    Button btn_chonanh,btn_add,btn_huy;
    Spinner spn_thietbi;
    ArrayAdapter spinerAdapter;
    String[] listTinhTrang = {"Tốt","Kém","Đang bảo trì","Hỏng"};
    String tinhtrang;
    int id;

    public ThietBiAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<ThietBi> list){
        this.list = list;
        this.listOld = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.item_thietbi,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThietBi thietBi = list.get(position);
        if (thietBi!=null){
            holder.tv_id.setText("Mã thiết bị: "+thietBi.getId());
            holder.tv_name.setText("Tên thiết bị: "+thietBi.getName());
            holder.tv_loai.setText("Loại: "+thietBi.getLoai());
            holder.tv_soluong.setText("Số lượng: "+thietBi.getSoLuong());
            holder.tv_hangsanxuat.setText("Hãng sản xuất: "+thietBi.getHangSanXuat());
            holder.tv_tinhtrang.setText("Tình trạng: "+thietBi.getTinhTrang());
            if (thietBi.getHinhanh()==null){
                holder.img_thietbi.setImageResource(R.drawable.ic_account);
            }else {
                String linkimg = thietBi.getHinhanh();
                Log.d("adapter",linkimg+" link");
                holder.img_thietbi.setImageDrawable(Drawable.createFromPath(linkimg));
            }
            holder.layout_update.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    new AlertDialog.Builder(context).setTitle("Xoá thiết bị ?")
                            .setMessage("Bạn có chắc chắn muốn xoá thiết bị ?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DuAn1DataBase.getInstance(context).thietBiDAO().delete(thietBi);
                                    list.remove(thietBi);
                                    notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("No",null)
                            .show();
                    return true;
                }
            });
            holder.layout_update.setOnClickListener(v -> {
                Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_addthietbi);
                dialog.show();
                Window window = dialog.getWindow();
                if (window == null){
                    return;
                }
                window.setBackgroundDrawable(null);
                edt_id = dialog.findViewById(R.id.edt_id_dialogthietbi);
                edt_name = dialog.findViewById(R.id.edt_ten_dialogthietbi);
                edt_loai = dialog.findViewById(R.id.edt_loai_dialogthietbi);
                edt_soluong = dialog.findViewById(R.id.edt_soLuong_dialogthietbi);
                edt_hangsanxuat = dialog.findViewById(R.id.edt_hangsanxuat_dialogthietbi);
                btn_chonanh = dialog.findViewById(R.id.btn_selectimage_dialogthietbi);
                spn_thietbi = dialog.findViewById(R.id.spn_tinhtrang_dialogthietbi);
                btn_add = dialog.findViewById(R.id.btn_luu_dialogthietbi);
                btn_huy = dialog.findViewById(R.id.btn_huy_dialogthietbi);

                spinerAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item,listTinhTrang);
                spn_thietbi.setAdapter(spinerAdapter);
                spn_thietbi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        tinhtrang = listTinhTrang[position];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                edt_id.setText(thietBi.getId()+"");
                edt_name.setText(thietBi.getName());
                edt_loai.setText(thietBi.getLoai());
                edt_soluong.setText(thietBi.getSoLuong()+"");
                for (int i = 0;i<listTinhTrang.length;i++){
                    if (listTinhTrang[i].equals(thietBi.getTinhTrang())){
                        id = i;
                    }
                }
                spn_thietbi.setSelection(id);
                edt_hangsanxuat.setText(thietBi.getHangSanXuat());
                if (thietBi.getHinhanh()==null){
                    holder.img_thietbi.setImageResource(R.drawable.ic_account);
                }else {
                    String linkimg = thietBi.getHinhanh();
                    Log.d("adapter",linkimg+" link");
                    holder.img_thietbi.setImageDrawable(Drawable.createFromPath(linkimg));
                }
                btn_huy.setOnClickListener(v1-> {
                    dialog.cancel();
                });
                btn_add.setOnClickListener(v1 -> {
                    if (validate()){
                        thietBi.setName(edt_name.getText().toString().trim());
                        thietBi.setHangSanXuat(edt_hangsanxuat.getText().toString().trim());
                        thietBi.setLoai(edt_loai.getText().toString().trim());
                        thietBi.setSoLuong(Integer.parseInt(edt_soluong.getText().toString().trim()));
                        thietBi.setTinhTrang(tinhtrang);
                        DuAn1DataBase.getInstance(context).thietBiDAO().update(thietBi);
                        Toast.makeText(context, "Update thiết bị thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        notifyDataSetChanged();
                    }
                });
            });
        }
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()){
                    list = listOld;
                }else {
                    List<ThietBi> listnew = new ArrayList<>();
                    for (ThietBi thietBi:listOld){
                        if (thietBi.getName().toLowerCase().contains(strSearch.toLowerCase())){
                            listnew.add(thietBi);
                        }
                    }
                    list = listnew;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (List<ThietBi>) results.values;
                notifyDataSetChanged();
            }
        };
    }
    private boolean validate(){
        if (edt_loai.getText().toString().trim().isEmpty() || edt_name.getText().toString().trim().isEmpty() || edt_soluong.getText().toString().trim().isEmpty() || edt_hangsanxuat.getText().toString().trim().isEmpty()){
            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            try {
                Integer.parseInt(edt_soluong.getText().toString().trim());
                return true;
            }catch (Exception e){
                Toast.makeText(context, "Số lượng không hợp lệ", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_id,tv_name,tv_loai,tv_soluong,tv_hangsanxuat,tv_tinhtrang;
        private ImageView img_thietbi;
        private RelativeLayout layout_update;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id_itemthietbi);
            tv_name = itemView.findViewById(R.id.tv_name_itemthietbi);
            tv_loai = itemView.findViewById(R.id.tv_loai_itemthietbi);
            tv_soluong = itemView.findViewById(R.id.tv_soluong_itemthietbi);
            tv_hangsanxuat = itemView.findViewById(R.id.tv_hangsanxuat_itemthietbi);
            tv_tinhtrang = itemView.findViewById(R.id.tv_tinhtrang_itemadmin);
            img_thietbi = itemView.findViewById(R.id.avt_itemthietbi);
            layout_update = itemView.findViewById(R.id.layout_update_itemthietbi);
        }
    }
}
