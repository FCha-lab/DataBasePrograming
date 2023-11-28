package com.example.databaseprograming;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class Main_Screen extends Fragment {


    //Screen Controller 변수 선언
    Screen_controller sc;

    //리사이클러뷰 - 진료과목별
    RecyclerView hospital_list;
    Main_hospital_type_recyclerAdapter hType_recycler;
    ArrayList<Integer> hospital_icon;
    ArrayList<String> hospital_text;

    //리사이클러뷰 - 추천
    RecyclerView recomend_list;
    Main_recommend_hospital_recyclerAdapter rList_recycler;
    ArrayList<String> recomend_type;
    ArrayList<String> recomend_name;
    ArrayList<String> recomend_info;

    //상단 아이콘
    ImageButton user_info_mani;

    //검색창
    EditText search_bar;
    ImageButton search_button;

    //3가지 아이콘
    ImageButton reserve_info;
    ImageButton diagno_info;
    ImageButton favorites;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.main_screen, container, false);

        //과목별 검색 recyclerview 리스트 추가
        hospital_icon = new ArrayList<>();
        hospital_icon.add(R.drawable.htype_ear);
        hospital_icon.add(R.drawable.htype_stomach);
        hospital_icon.add(R.drawable.htype_bone);
        hospital_icon.add(R.drawable.htype_eye);
        hospital_icon.add(R.drawable.htype_kid);
        hospital_icon.add(R.drawable.htype_pragnant);
        hospital_icon.add(R.drawable.htype_nerve);
        hospital_icon.add(R.drawable.htype_skin);
        hospital_icon.add(R.drawable.htype_mind);
        //hospital_icon.add(R.drawable.imageName);

        hospital_text = new ArrayList<>(
                Arrays.asList("이비인후과", "내과", "정형외과", "안과", "소아청소년과", "산부인과", "신경외과", "피부과", "정신건강의학과")
        );

        //추천 병원 recyclerview 리스트 추가
        recomend_type = new ArrayList<>(
                Arrays.asList("이비인후과", "이비인후과", "이비인후과")
        );
        recomend_name = new ArrayList<>(
                Arrays.asList("병원이에용", "한의원이에용", "모르겠어용")
        );
        recomend_info = new ArrayList<>(
                Arrays.asList("정보에양", "뭐일까용", "요구사항이 ㅈㄴ 많아영")
        );


        //리사이클러뷰 연결
        hospital_list = rootView.findViewById(R.id.hospital_type_recycle);
        recomend_list = rootView.findViewById(R.id.recomend_recycle);

        //리사이클러뷰 어댑터 초기화
        hospital_list.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        hType_recycler = new Main_hospital_type_recyclerAdapter(hospital_icon, hospital_text);
        hospital_list.setAdapter(hType_recycler);


        recomend_list.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        rList_recycler = new Main_recommend_hospital_recyclerAdapter(recomend_type, recomend_name, recomend_info);
        recomend_list.setAdapter(rList_recycler);

        //리사이클러뷰 어댑터 가로 스크롤바 없애기
        hospital_list.setHorizontalScrollBarEnabled(false);
        recomend_list.setHorizontalScrollBarEnabled(false);


        //위젯을 연결
        user_info_mani = rootView.findViewById(R.id.user_info_mani);

        search_bar = rootView.findViewById(R.id.search_textview);
        search_button = rootView.findViewById(R.id.search_button);

        reserve_info = rootView.findViewById(R.id.reserve_info);
        diagno_info = rootView.findViewById(R.id.diagno_info);
        favorites = rootView.findViewById(R.id.favorites);


        //버튼에 대한 리스너 등록

        user_info_mani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //회원 정보 페이지 이동 시도
                if(sc.getToken() != null){
                    //로그인 되어있을 경우
                    Log.d("메인 전환 확인", sc.getToken());
                    sc.replaceFragment(new Modification_Screen());
                }else {
                    //로그인 안 되어있을 경우
                    sc.replaceFragment(new Login_Screen());
                }
            }
        });

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        reserve_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //예약정보 이미지를 눌렀을 때
                sc.replaceFragment(new Reservation_Screen());

            }
        });

        diagno_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sc.replaceFragment(new Medical_Records_Inquiry_Screen());
            }
        });

        favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sc.replaceFragment(new Hospital_Search_Results_Screen());
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
