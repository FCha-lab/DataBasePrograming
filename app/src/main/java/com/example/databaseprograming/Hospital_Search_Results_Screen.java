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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class Hospital_Search_Results_Screen extends Fragment {


    //Screen Controller 변수 선언
    Screen_controller sc;

    //리사이클러뷰 - 진료과목별
    RecyclerView hsr_list;
    Hospital_Search_Results_recyclerAdapter hsrType_recycler;
    ArrayList<String> hsr_text1;
    ArrayList<String> hsr_text2;
    ArrayList<Integer> hsr_text3; // 좋아요 수를 저장하는 리스트
    ArrayList<String> hsr_text4;
    ArrayList<String> hsr_text5;
    ArrayList<String> hsr_text6;

    //검색창
    EditText search_bar;
    ImageButton search_button;

    //페이지 관련 위젯 변수 선언
    private ImageView page_back;

    public Hospital_Search_Results_Screen(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.hospital_search_results, container, false);

        hsr_text1 = new ArrayList<>(
                Arrays.asList("천안 오케이 내과","천안 오케이 비뇨기과","천안 오케이 이비인후과")
        );
        hsr_text2 = new ArrayList<>(
                Arrays.asList("내과","비뇨기과","이비인후과")
        );
        hsr_text3 = new ArrayList<>(
                Arrays.asList(50,60,70)
        );
        hsr_text4 = new ArrayList<>(
                Arrays.asList("09:00~18:00","10:00~18:00","11:00~19:00")
        );
        hsr_text5 = new ArrayList<>(
                Arrays.asList("041-553-2479","041-553-2499","041-553-5500")
        );
        hsr_text6 = new ArrayList<>(
                Arrays.asList("충청남도 천안시 서북구...","충청남도 천안시 동북구...","충청남도 천안시 남구...")
        );


        //리사이클러뷰 연결
        hsr_list = rootView.findViewById(R.id.hsr_recycle);

        //리사이클러뷰 어댑터 초기화
        hsr_list.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        hsrType_recycler = new Hospital_Search_Results_recyclerAdapter(hsr_text1, hsr_text2, hsr_text3, hsr_text4, hsr_text5, hsr_text6);
        hsr_list.setAdapter(hsrType_recycler);

        //리사이클러뷰 어댑터 가로 스크롤바 없애기
        hsr_list.setVerticalScrollBarEnabled(false);

        //위젯을 연결
        search_bar = rootView.findViewById(R.id.search_textview);
        search_button = rootView.findViewById(R.id.search_button);
        
        //뒤로가기 버튼 연결
        page_back = rootView.findViewById(R.id.page_back);

        //버튼에 대한 리스너 등록

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
