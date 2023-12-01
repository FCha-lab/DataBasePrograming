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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;


public class Medical_Records_Screen extends Fragment {

    //Screen Controller 변수 선언
    private Screen_controller sc;

    //병원 정보 위젯 변수 선언
    private TextView user_birthdate;
    private TextView user_name;
    private TextView medical_record_date;
    private TextView hospital_name;

    private TextView hospital_phone_number;
    private TextView like_count;
    private TextView hospital_doctor_name;
    private TextView hospital_doctor_license;
    private TextView doctor_opinion;

    //페이지 관련 위젯 변수 선언
    private ImageView page_back;

    //상호작용 관련 위젯 변수 선언
    private ImageButton hospital_like;

    //페이지 내 처리 변수 선언
    private boolean isHospitalLiked;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.medical_records, container, false);

        //리사이클러뷰 연결
        //리사이클러뷰 어댑터 초기화

        //위젯을 연결
        user_birthdate = rootView.findViewById(R.id.user_birthdate);
        user_name = rootView.findViewById(R.id.user_name);
        medical_record_date = rootView.findViewById(R.id.medical_record_date);
        hospital_name = rootView.findViewById(R.id.hospital_name);

        hospital_phone_number = rootView.findViewById(R.id.hospital_phone_number);
        like_count = rootView.findViewById(R.id.like_count);
        hospital_doctor_name = rootView.findViewById(R.id.hospital_doctor_name);
        hospital_doctor_license = rootView.findViewById(R.id.hospital_doctor_license);
        doctor_opinion = rootView.findViewById(R.id.doctor_opinion);

        hospital_like = rootView.findViewById(R.id.hospital_like);

        //뒤로가기 버튼 연결
        page_back = rootView.findViewById(R.id.page_back);

        //위젯의 정보 초기화
//        user_birthdate.setText("");
//        user_name.setText("");
//        medical_record_date.setText("");
//        hospital_name.setText("");
//
//        hospital_phone_number.setText("");
//        like_count.setText("");
//        hospital_doctor_name.setText("");
//        hospital_doctor_license.setText("");
//        doctor_opinion.setText("");

        //상단 툴바 뒤로가기 버튼을 누를 경우
        page_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sc.onBackPressed();
            }
        });

        //페이지 내 처리 변수 초기화
        isHospitalLiked = false;

        //버튼에 대한 리스너 등록
        hospital_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //좋아요 버튼을 눌렀을 때
                if (isHospitalLiked) {
                    //만약 이미 좋아요 상태라면?
                    hospital_like.setImageResource(R.drawable.heart_empty);
                    like_count.setText("");
                } else {
                    //만약 좋아요를 누르지 않은 상태였다면?
                    hospital_like.setImageResource(R.drawable.heart_full);
                    like_count.setText("");
                }
            }
        });

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