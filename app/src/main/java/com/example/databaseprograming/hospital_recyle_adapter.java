package com.example.databaseprograming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class hospital_recyle_adapter extends RecyclerView.Adapter<hospital_recyle_adapter.ViewHolder> {

    private ArrayList<String> hospital_text_list;
    private ArrayList<Integer> hospital_icon_list;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageButton hospital_icon;
        public TextView hospital_text;

        public ViewHolder(@NonNull View item){
            super(item);
        }
    }

    public hospital_recyle_adapter (ArrayList<String> tlist, ArrayList<Integer> ilist){
        hospital_icon_list = ilist;
        hospital_text_list = tlist;
    }

    @NonNull
    @Override// ViewHolder 객체를 생성하여 리턴한다.
    public hospital_recyle_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        hospital_recyle_adapter.ViewHolder viewHolder = new hospital_recyle_adapter.ViewHolder(view);

        return viewHolder;
    }

    @Override   // ViewHolder안의 내용을 position에 해당되는 데이터로 교체한다.
    public void onBindViewHolder(@NonNull hospital_recyle_adapter.ViewHolder holder, int position) {
        String text = localDataSet.get(position);
        holder.textView.setText(text);
    }

    @Override   // 전체 데이터의 갯수를 리턴한다.
    public int getItemCount() {
        return localDataSet.size();
    }
}
