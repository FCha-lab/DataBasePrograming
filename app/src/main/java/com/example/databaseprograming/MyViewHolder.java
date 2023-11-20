package com.example.databaseprograming;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout firstLinearLayout;
    public LinearLayout secondLinearLayout;
    public TextView textViewTitle1;
    public TextView textViewTitle2;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        // 각 LinearLayout과 뷰들을 초기화합니다.
        firstLinearLayout = itemView.findViewById(R.id.item_his_ll_1);
        secondLinearLayout = itemView.findViewById(R.id.secondLinearLayout);
        //textViewTitle1 = itemView.findViewById(R.id.hospital_doctor_name);
        textViewTitle2 = itemView.findViewById(R.id.textViewTitle2);
    }
}


