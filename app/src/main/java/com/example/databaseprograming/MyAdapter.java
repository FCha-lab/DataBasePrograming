package com.example.databaseprograming;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    // 데이터 리스트를 선언합니다.

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 뷰홀더 객체를 생성하고 반환합니다.
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // 뷰홀더에 데이터를 바인딩합니다.
        // 예를 들어, 데이터 리스트에서 해당 위치(position)의 데이터를 가져와 뷰에 설정합니다.
        // holder.textViewTitle1.setText(dataList.get(position).getTitle1());
        // holder.textViewTitle2.setText(dataList.get(position).getTitle2());
        // 기타 뷰들도 설정합니다.
    }

    @Override
    public int getItemCount() {
        // 데이터 리스트의 크기를 반환합니다.
        return 0;
    }
}


