package com.example.databaseprograming;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Hospital_Info_Screen extends Fragment {

    //Screen Controller 변수 선언
    private Screen_controller sc;

    //기본 병원 정보 위젯 변수 선언
    private TextView opening_hour;
    private TextView hospital_type;
    private TextView hospital_name;
    private TextView like_count;

    //상세 병원 정보 위젯 변수 선언
    private TextView hospital_adress;
    private TextView hospital_phone_number;
    private TextView hospital_open_date;
    private TextView hospital_opening_hour;
    private TextView hospital_breaktime;

    //의사 정보 리사이클러뷰 관련 변수 선언
    private RecyclerView hospital_doctor_list;
    private Hospital_info_doctor_recycleradapter hDoctor_recycler;

    //상호작용 관련 위젯 변수 선언
    private ImageButton hospital_like;
    private Button reservation_button;

    //페이지 내 처리 변수 선언

    private boolean isHospitalLiked;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.hospital_info_screen, container, false);

        //리사이클러뷰 연결
        hospital_doctor_list = rootView.findViewById(R.id.hospital_doctor_recycle);

        //리사이클러뷰 어댑터 초기화
        hospital_doctor_list.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        hDoctor_recycler = new Hospital_info_doctor_recycleradapter();
        hospital_doctor_list.setAdapter(hDoctor_recycler);

        //리사이클러뷰 어댑터 가로 스크롤바 없애기
        hospital_doctor_list.setVerticalScrollBarEnabled(false);

        //위젯을 연결
        opening_hour = rootView.findViewById(R.id.opening_hour);
        hospital_type = rootView.findViewById(R.id.hospital_type);
        hospital_name = rootView.findViewById(R.id.hospital_name);
        like_count = rootView.findViewById(R.id.like_count);

        hospital_adress = rootView.findViewById(R.id.hospital_adress);
        hospital_phone_number = rootView.findViewById(R.id.hospital_phone_number);
        hospital_open_date = rootView.findViewById(R.id.hospital_open_date);
        hospital_opening_hour = rootView.findViewById(R.id.hospital_opening_hour);
        hospital_breaktime = rootView.findViewById(R.id.hospital_breaktime);

        hospital_like = rootView.findViewById(R.id.hospital_like);
        reservation_button = rootView.findViewById(R.id.reservation_button);

        //위젯의 정보 초기화
        opening_hour.setText("");
        hospital_type.setText("");
        hospital_name.setText("");
        like_count.setText("");

        hospital_adress.setText("");
        hospital_phone_number.setText("");
        hospital_open_date.setText("");
        hospital_opening_hour.setText("");
        hospital_breaktime.setText("");

        //페이지 내 처리 변수 초기화
        isHospitalLiked = false;


        //버튼에 대한 리스너 등록
        hospital_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //좋아요 버튼을 눌렀을 때
                if(isHospitalLiked){
                    //만약 이미 좋아요 상태라면?
                    hospital_like.setImageResource(R.drawable.heart_full);
                    like_count.setText("");
                }else{
                    //만약 좋아요를 누르지 않은 상태였다면?
                    hospital_like.setImageResource(R.drawable.heart_empty);
                    like_count.setText("");
                }
            }
        });

        reservation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
