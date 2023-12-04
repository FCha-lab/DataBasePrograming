package com.example.databaseprograming;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Main_recommend_hospital_recyclerAdapter extends RecyclerView.Adapter<Main_recommend_hospital_recyclerAdapter.ViewHolder>{


    private ArrayList<Main_Recommend_Response> recommendList;
    private Screen_controller sc;
    private boolean linkOn;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1 ;
        TextView textView2 ;
        TextView textView3 ;

        LinearLayout frame;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            textView1 = itemView.findViewById(R.id.recomend_hospital_type);
            textView2 = itemView.findViewById(R.id.recomend_hospital_name);
            textView3 = itemView.findViewById(R.id.recomend_hospital_info);

            frame = itemView.findViewById(R.id.frame);
        }


    }

    Main_recommend_hospital_recyclerAdapter(ArrayList<Main_Recommend_Response> list, Screen_controller sc, boolean linkOn) {
        recommendList = list;
        this.sc = sc;
        this.linkOn = linkOn;
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

        holder.frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //배너를 눌렀을 경우
                if(!linkOn){
                    //정상적인 배너가 아닐경우
                    return;
                }else{
                    //정상적인 배너일 경우
                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                    //번들에 넘길 값 저장
                    bundle.putString("id", item.getId());
                    Fragment target = sc.getScreen(new Hospital_Info_Screen());//프래그먼트 선언
                    target.setArguments(bundle);//번들을 프래그먼트로 보낼 준비

                    sc.replaceFragment(new Hospital_Info_Screen());
                }

            }
        });
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return recommendList.size() ;
    }
}
