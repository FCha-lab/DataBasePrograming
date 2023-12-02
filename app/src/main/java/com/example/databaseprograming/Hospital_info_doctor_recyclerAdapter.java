package com.example.databaseprograming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Hospital_info_doctor_recyclerAdapter extends RecyclerView.Adapter<Hospital_info_doctor_recyclerAdapter.ViewHolder> {

    private ArrayList<Hospital_Info_Response.Doctor_Info> doctor_info_list;

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView1;
        TextView textView1;
        TextView textView2;

        ViewHolder(View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조. (hold strong reference)
            imageView1 = itemView.findViewById(R.id.color_dot);
            textView1 = itemView.findViewById(R.id.hospital_doctor_name);
            textView2 = itemView.findViewById(R.id.hospital_doctor_university);


        }

    }

    Hospital_info_doctor_recyclerAdapter(ArrayList<Hospital_Info_Response.Doctor_Info> list) {
        doctor_info_list = list;
    }


    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public Hospital_info_doctor_recyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_his, parent, false);
        Hospital_info_doctor_recyclerAdapter.ViewHolder vh = new Hospital_info_doctor_recyclerAdapter.ViewHolder(view);

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(Hospital_info_doctor_recyclerAdapter.ViewHolder holder, int position) {
        Hospital_Info_Response.Doctor_Info item = doctor_info_list.get(position);

        if (item.getGender().equals("M")) {
            //남자일 경우
            holder.imageView1.setImageResource(R.drawable.blue_dot);
        } else if(item.getGender().equals("F")){
            //여자일 경우
            holder.imageView1.setImageResource(R.drawable.red_dot);
        }
        holder.textView1.setText(item.getDoctorName());
        holder.textView2.setText(item.getBiography());

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return doctor_info_list.size();
    }

}
