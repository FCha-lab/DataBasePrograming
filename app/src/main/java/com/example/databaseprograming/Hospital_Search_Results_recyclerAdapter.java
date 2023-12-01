package com.example.databaseprograming;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.Collections;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Hospital_Search_Results_recyclerAdapter extends RecyclerView.Adapter<Hospital_Search_Results_recyclerAdapter.ViewHolder>{

    private List<Hospital_Search_Result> resultList;

    private Screen_controller sc;


    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        ImageView imageView1;

        LinearLayout frame;


        ViewHolder(View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조. (hold strong reference)
            textView1 = itemView.findViewById(R.id.hospital_name);
            textView2 = itemView.findViewById(R.id.hospital_kind);
            textView3 = itemView.findViewById(R.id.like_count);
            textView4 = itemView.findViewById(R.id.hospital_operation_time);
            textView5 = itemView.findViewById(R.id.hospital_phone_number);
            textView6 = itemView.findViewById(R.id.hospital_adress);
            imageView1 = itemView.findViewById(R.id.hospital_like);

            frame = itemView.findViewById(R.id.hsr_button_1);

        }
    }

    Hospital_Search_Results_recyclerAdapter(ArrayList<Hospital_Search_Result> list, Screen_controller sc) {
        resultList = list;

        this.sc = sc;

    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public Hospital_Search_Results_recyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_hsr, parent, false);
        Hospital_Search_Results_recyclerAdapter.ViewHolder vh = new Hospital_Search_Results_recyclerAdapter.ViewHolder(view);
        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(Hospital_Search_Results_recyclerAdapter.ViewHolder holder, int position) {
        Hospital_Search_Result item = resultList.get(position);

        holder.textView1.setText(item.getName());
        holder.textView2.setText(item.getDepartment());
        holder.textView3.setText(String.valueOf(item.getLikeCount()));
        holder.textView4.setText(item.getOperatingHours());
        holder.textView5.setText(item.getPhoneNumber());
        holder.textView6.setText(item.getAddress());

        holder.frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //개체를 눌렀을 때
            }
        });

    }

    // getItemCount() - 전체 데이터 개수 리턴.
    @Override
    public int getItemCount() {
        return resultList.size();
    }
}

