package com.example.duan1_baove.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.util.DebugUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_baove.Admin_MainActivity;
import com.example.duan1_baove.R;
import com.example.duan1_baove.database.DuAn1DataBase;
import com.example.duan1_baove.model.ThongBao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class ThongBaoAdapter extends RecyclerView.Adapter<ThongBaoAdapter.ViewHolder> {
    private Context context;
    private List<ThongBao> list;

    private EditText edt_id,edt_title,edt_content;
    private Button btn_add,btn_huy;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy,HH:mm:ss", Locale.getDefault());
    String currentDateandTime = sdf.format(new Date());

    public ThongBaoAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<ThongBao> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_thongbao,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThongBao thongBao = list.get(position);
        if (thongBao!=null){
            holder.tv_title.setText("Tiêu đề: \n"+thongBao.getTieude());
            holder.tv_content.setText("Nội dung: \n"+thongBao.getNoidung());
            holder.tv_time.setText(thongBao.getThoigian());
            String user = DuAn1DataBase.getInstance(context).adminDAO().getName(thongBao.getUser_id());
            holder.tv_user.setText(user);

            if (DuAn1DataBase.getInstance(context).adminDAO().checkaccount(thongBao.getUser_id()).get(0).getHinhanh()==null){
                holder.img_user.setImageResource(R.drawable.ic_account);
            }else {
                String linkimg = DuAn1DataBase.getInstance(context).adminDAO().checkaccount(thongBao.getUser_id()).get(0).getHinhanh();
                Log.d("adapter",linkimg+" link");
                holder.img_user.setImageDrawable(Drawable.createFromPath(linkimg));
            }
            holder.layout_update.setOnLongClickListener(v -> {
                new AlertDialog.Builder(context).setTitle("Xoá thông báo ?")
                        .setMessage("Bạn có chắc chắn muốn xoá thông báo ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DuAn1DataBase.getInstance(context).thongBaoDAO().delete(thongBao);
                                list.remove(thongBao);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
                return true;
            });
            holder.layout_update.setOnClickListener(v -> {
                Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_addthongbao);
                dialog.show();
                Window window = dialog.getWindow();
                if (window == null){
                    return;
                }
                window.setBackgroundDrawable(null);
                edt_id = dialog.findViewById(R.id.edt_mathongbao_dialogthongbao);
                edt_title = dialog.findViewById(R.id.edt_tieude_dialogthongbao);
                edt_content = dialog.findViewById(R.id.edt_noidung_dialogthongbao);
                btn_add = dialog.findViewById(R.id.btn_luu_dialogthongbao);
                btn_huy = dialog.findViewById(R.id.btn_huy_dialogthongbao);

                edt_id.setText(thongBao.getId()+"");
                edt_title.setText(thongBao.getTieude());
                edt_content.setText(thongBao.getNoidung());

                btn_huy.setOnClickListener(v1 -> {
                    dialog.cancel();
                });
                btn_add.setOnClickListener(v1 -> {
                    if (validate()){
                        thongBao.setTieude(edt_title.getText().toString().trim());
                        thongBao.setNoidung(edt_content.getText().toString().trim());
                        thongBao.setUser_id(Admin_MainActivity.user);
                        thongBao.setThoigian(currentDateandTime);
                        DuAn1DataBase.getInstance(context).thongBaoDAO().update(thongBao);
                        Toast.makeText(context, "Update thông báo thành công ", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
            });
        }
    }

    @Override
    public int getItemCount() {
        if (list !=null){
            return list.size();
        }
        return 0;
    }
    private boolean validate(){
        if (edt_title.getText().toString().trim().isEmpty() || edt_content.getText().toString().trim().isEmpty()){
            Toast.makeText(context, "Vui lòng không bỏ trống thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_id,tv_user,tv_time,tv_title,tv_content;
        private CircleImageView img_user;
        private RelativeLayout layout_update;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.edt_mathongbao_dialogthongbao);
            tv_user = itemView.findViewById(R.id.tv_tenuser_itemthongbao);
            tv_time = itemView.findViewById(R.id.tv_time_itemthongbao);
            tv_title = itemView.findViewById(R.id.tv_title_itemthongbao);
            tv_content = itemView.findViewById(R.id.tv_content_itemthongbao);
            img_user = itemView.findViewById(R.id.avt_itemthongbao);
            layout_update = itemView.findViewById(R.id.layout_update_itemthongbao);
        }
    }
}
