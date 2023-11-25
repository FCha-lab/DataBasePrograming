package com.example.databaseprograming;

import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class Inquiry_of_Reservation_Information_Screen extends Fragment {


    //Screen Controller 변수 선언
    Screen_controller sc;

    //리사이클러뷰 - 진료과목별
    RecyclerView ior_list;
    Inquiry_of_Reservation_Information_recyclerAdapter iorType_recycler;
    ArrayList<String> ior_text1;
    ArrayList<String> ior_text2;
    ArrayList<String> ior_text3;
    ArrayList<String> ior_text4;

    //검색창
    EditText search_bar;
    ImageButton search_button;

    //페이지 관련 위젯 변수 선언
    private ImageView page_back;

    public Inquiry_of_Reservation_Information_Screen(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.inquiry_of_reservation_information, container, false);

        ior_text1 = new ArrayList<>(
                Arrays.asList("진료전","진료완료","진료전")
        );
        ior_text2 = new ArrayList<>(
                Arrays.asList("천안오케이내과의원","천안오케이비뇨기과의원","천안오케이이비인후과의원")
        );
        ior_text3 = new ArrayList<>(
                Arrays.asList("충청남도 천안시 서북구...","충청남도 천안시 서북구...","충청남도 천안시 서북구...")
        );
        ior_text4 = new ArrayList<>(
                Arrays.asList("2023.10.31 14:00","2023.12.31 15:00","2023.11.29 11:00")
        );


        //리사이클러뷰 연결
        ior_list = rootView.findViewById(R.id.ior_recycle);

        //리사이클러뷰 어댑터 초기화
        ior_list.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        iorType_recycler = new Inquiry_of_Reservation_Information_recyclerAdapter(ior_text1, ior_text2, ior_text3, ior_text4);
        ior_list.setAdapter(iorType_recycler);

        //리사이클러뷰 어댑터 가로 스크롤바 없애기
        ior_list.setVerticalScrollBarEnabled(false);

        //위젯을 연결
        search_bar = rootView.findViewById(R.id.search_textview);
        search_button = rootView.findViewById(R.id.search_button);

        //뒤로가기 버튼 연결
        page_back = rootView.findViewById(R.id.page_back);

        //버튼에 대한 리스너 등록
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //상단 툴바 뒤로가기 버튼을 누를 경우
        page_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sc.onBackPressed();
            }
        });

//        reserve_info.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //예약정보 이미지를 눌렀을 때
//                sc.replaceFragment(sc.getScreen(new Reservation_Screen()));
//
//            }
//        });


        return rootView;
    }

    //Screen Controller 초기화 및 메모리 해제
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Screen_controller) {
            sc = (Screen_controller) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement Screen_controller");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sc = null; // 참조 해제
    }

}
