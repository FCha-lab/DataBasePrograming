package com.example.databaseprograming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Hospital_info_doctor_recycleradapter extends RecyclerView.Adapter<Hospital_info_doctor_recycleradapter.ViewHolder> {

    private ArrayList<Integer> typeIcon = null;
    private ArrayList<String> typeText = null;

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton imageButton1;

        ViewHolder(View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조. (hold strong reference)
            imageButton1 = itemView.findViewById(R.id.main_recycler_hospital_icon);

        }

    }

    Hospital_info_doctor_recycleradapter() {

    }


    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public Hospital_info_doctor_recycleradapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout. , parent, false) ;
        Hospital_info_doctor_recycleradapter.ViewHolder vh = new Hospital_info_doctor_recycleradapter.ViewHolder(view) ;

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(Main_hospital_type_recycleradapter.ViewHolder holder, int position) {
        Integer image = typeIcon.get(position);
        String text = typeText.get(position) ;

        holder.imageButton1.setImageResource(image);
        holder.textView1.setText(text) ;


    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return typeIcon.size() ;
    }

}
