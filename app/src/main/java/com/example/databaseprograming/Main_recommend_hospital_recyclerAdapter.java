package com.example.databaseprograming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Main_recommend_hospital_recyclerAdapter extends RecyclerView.Adapter<Main_recommend_hospital_recyclerAdapter.ViewHolder>{


    private ArrayList<Main_Recommend_Response> recommendList;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1 ;
        TextView textView2 ;
        TextView textView3 ;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            textView1 = itemView.findViewById(R.id.recomend_hospital_type);
            textView2 = itemView.findViewById(R.id.recomend_hospital_name);
            textView3 = itemView.findViewById(R.id.recomend_hospital_info);
        }


    }

    Main_recommend_hospital_recyclerAdapter(ArrayList<Main_Recommend_Response> list) {
        recommendList = list;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public Main_recommend_hospital_recyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.hospital_recomendation, parent, false) ;
        Main_recommend_hospital_recyclerAdapter.ViewHolder vh = new Main_recommend_hospital_recyclerAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(Main_recommend_hospital_recyclerAdapter.ViewHolder holder, int position) {
        Main_Recommend_Response item = recommendList.get(position);


        holder.textView1.setText(item.getDepartment());
        holder.textView2.setText(item.getHName());
        holder.textView3.setText(item.getAddress());

        holder.textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return recommendList.size() ;
    }
}
