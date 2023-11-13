package com.example.databaseprograming;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class Main_Screen extends AppCompatActivity {

    RecyclerView hospital_list;
    Main_hospital_type_recycleradapter hType_recycler;
    ArrayList<Integer> hospital_icon;
    ArrayList<String> hospital_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        //과목별 recyclerview 리스트 추가
        hospital_text = new ArrayList<>(
                Arrays.asList("이비인후과", "내과", "정형외과", "안과", "소아청소년과", "산부인과", "신경외과", "피부과", "정신건강의학과")
        );
        hospital_icon = new ArrayList<>();
        //integerArrayList.add(R.drawable.imageName);

        //리사이클러뷰 연결
        hospital_list = findViewById(R.id.hospital_type_recycle);

        //리사이클러뷰 어댑터 초기화
        hospital_list.setLayoutManager(new LinearLayoutManager(this));
        hType_recycler = new Main_hospital_type_recycleradapter(hospital_icon, hospital_text);
        hospital_list.setAdapter(hType_recycler);


    }
}
