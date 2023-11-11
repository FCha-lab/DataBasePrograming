package com.example.databaseprograming;

import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_Screen extends AppCompatActivity {

    RecyclerView hospital_list;
    hospital_recyle_adapter hadapter;
    ArrayList<String> hospital_text;
    ArrayList<Integer> hospital_icon;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.main_screen);

        //과목별 recyclerview 리스트 추가
        hospital_text = new ArrayList<>(
                Arrays.asList("이비인후과", "내과", "정형외과", "안과", "소아과")
        );
        hospital_icon = new ArrayList<>();
        //integerArrayList.add(R.drawable.imageName);

        //리사이클러뷰 연결
        hospital_list = findViewById(R.id.hospital_type_recycle);

        //리사이클러뷰 어댑터 초기화
        hadapter = new hospital_recyle_adapter(hospital_text, hospital_icon);

        //리사이클러뷰 세팅
        hospital_list.setAdapter(hadapter);
        hospital_list.setLayoutManager(new LinearLayoutManager(this));





    }
}
