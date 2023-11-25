package com.example.databaseprograming;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Reservation_Screen extends Fragment {


    //Screen Controller 변수 선언
    private Screen_controller sc;

    //병원 관련 위젯 변수 선언
    private TextView hospital_name;

    //예약 관련 위젯 변수 선언
    private CalendarView select_date;

    private Button reservation_button;
    private RadioButton[] buttonList;
    private RadioButton current_button;

    //페이지 관련 위젯 변수 선언
    private ImageView page_back;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.reservation_screen, container, false);


        //위젯을 연결
        hospital_name = rootView.findViewById(R.id.hospital_name);

        select_date =  rootView.findViewById(R.id.select_date);

        buttonList = new RadioButton[16];
        buttonList[0] = rootView.findViewById(R.id.am1);
        buttonList[1] = rootView.findViewById(R.id.am2);
        buttonList[2] = rootView.findViewById(R.id.am3);
        buttonList[3] = rootView.findViewById(R.id.am4);
        buttonList[4] = rootView.findViewById(R.id.am5);
        buttonList[5] = rootView.findViewById(R.id.am6);
        buttonList[6] = rootView.findViewById(R.id.am7);
        buttonList[7] = rootView.findViewById(R.id.am8);
        buttonList[8] = rootView.findViewById(R.id.pm1);
        buttonList[9] = rootView.findViewById(R.id.pm2);
        buttonList[10] = rootView.findViewById(R.id.pm3);
        buttonList[11] = rootView.findViewById(R.id.pm4);
        buttonList[12] = rootView.findViewById(R.id.pm5);
        buttonList[13] = rootView.findViewById(R.id.pm6);
        buttonList[14] = rootView.findViewById(R.id.pm7);
        buttonList[15] = rootView.findViewById(R.id.pm8);

        reservation_button = rootView.findViewById(R.id.reservation_button);

        page_back = rootView.findViewById(R.id.page_back);

        //페이지 관련 변수 초기화
        current_button = null;
        hospital_name.setText("병원 이름");


        //버튼에 대한 리스너 등록
        for(int i = 0 ; i < buttonList.length; i++){
            buttonList[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(current_button != null){
                        current_button.setChecked(false);
                    }

                    if(current_button == (RadioButton) view){
                        current_button.setChecked(false);
                        current_button = null;
                    }else{
                        current_button = (RadioButton) view;
                        current_button.setChecked(true);
                    }

                }
            });
        }

        page_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //상단 툴바 위로가기 버튼을 누를 경우
                sc.onBackPressed();
            }
        });

        reservation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //예약 버튼을 눌렀을 때

            }
        });

        return  rootView;
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
