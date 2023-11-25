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

public class Medical_Records_Inquiry_Screen extends Fragment {


    //Screen Controller 변수 선언
    Screen_controller sc;

    //리사이클러뷰 - 진료과목별
    RecyclerView mri_list;
    Medical_Records_Inquiry_recyclerAdapter mriType_recycler;
    ArrayList<String> mri_text1;
    ArrayList<String> mri_text2;
    ArrayList<String> mri_text3;

    //검색창
    EditText search_bar;
    ImageButton search_button;

    //페이지 관련 위젯 변수 선언
    private ImageView page_back;

    public Medical_Records_Inquiry_Screen(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.medical_records_inquiry, container, false);

        mri_text1 = new ArrayList<>(
                Arrays.asList("2023.10.31","2023.11.01","2023.11.11")
        );
        mri_text2 = new ArrayList<>(
                Arrays.asList("천안오케이내과의원","천안오케이비뇨기과의원","천안오케이이비인후과의원")
        );
        mri_text3 = new ArrayList<>(
                Arrays.asList("장현희","차경환","박진용")
        );


        //리사이클러뷰 연결
        mri_list = rootView.findViewById(R.id.mri_recycle);

        //리사이클러뷰 어댑터 초기화
        mri_list.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        mriType_recycler = new Medical_Records_Inquiry_recyclerAdapter(mri_text1, mri_text2, mri_text3);
        mri_list.setAdapter(mriType_recycler);

        //리사이클러뷰 어댑터 가로 스크롤바 없애기
        mri_list.setVerticalScrollBarEnabled(false);

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
