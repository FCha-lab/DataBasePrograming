package com.example.databaseprograming;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Main_hospital_type_recyclerAdapter extends RecyclerView.Adapter<Main_hospital_type_recyclerAdapter.ViewHolder>{

    private Screen_controller sc;
    private ArrayList<Integer> typeIcon = null;
    private ArrayList<String> typeText = null;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton imageButton1;
        TextView textView1 ;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            imageButton1 = itemView.findViewById(R.id.main_recycler_hospital_icon);
            textView1 = itemView.findViewById(R.id.main_recycler_hospital_text);
        }


    }

    Main_hospital_type_recyclerAdapter(ArrayList<Integer> tIcon, ArrayList<String> tText, Screen_controller sc) {
         typeIcon = tIcon;
         typeText = tText;
         this.sc = sc;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public Main_hospital_type_recyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.hospital_type, parent, false) ;
        Main_hospital_type_recyclerAdapter.ViewHolder vh = new Main_hospital_type_recyclerAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(Main_hospital_type_recyclerAdapter.ViewHolder holder, int position) {
        Integer image = typeIcon.get(position);
        String text = typeText.get(position) ;

        holder.imageButton1.setImageResource(image);
        holder.textView1.setText(text) ;

        holder.imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //버튼을 누른다면?
                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                //번들에 넘길 값 저장
                bundle.putString("searchInfo", text);
                bundle.putBoolean("isSearchBar", false);
                Fragment target = sc.getScreen(new Hospital_Search_Results_Screen());//프래그먼트 선언
                target.setArguments(bundle);//번들을 프래그먼트로 보낼 준비

                sc.replaceFragment(new Hospital_Search_Results_Screen(), true);

            }
        });

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return typeIcon.size() ;
    }
}
