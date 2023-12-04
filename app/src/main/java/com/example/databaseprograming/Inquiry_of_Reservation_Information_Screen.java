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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    //서버 관련 변수 선언
    Inquiry_of_Reservation_Information_RetrofitClient inquiry_of_reservation_information_retrofitClient;

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

        //리사이클러뷰 어댑터 가로 스크롤바 없애기
        ior_list.setVerticalScrollBarEnabled(false);

        //위젯을 연결
        search_bar = rootView.findViewById(R.id.search_textview);
        search_button = rootView.findViewById(R.id.search_button);

        //뒤로가기 버튼 연결
        page_back = rootView.findViewById(R.id.page_back);

        //서버 관련 변수 초기화
        inquiry_of_reservation_information_retrofitClient = new Inquiry_of_Reservation_Information_RetrofitClient();

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

    @Override
    public void onResume() {
        super.onResume();

        Inquiry_of_Reservation_Informaion_RetrofitInterface inquiry_of_reservation_informaion_retrofitInterface = inquiry_of_reservation_information_retrofitClient.getApiService(sc.getToken());

        inquiry_of_reservation_informaion_retrofitInterface.getIOR().enqueue(new Callback<ArrayList<Inquiry_of_Reservation_Informaion_Response>>() {
            @Override
            public void onResponse(Call<ArrayList<Inquiry_of_Reservation_Informaion_Response>> call, Response<ArrayList<Inquiry_of_Reservation_Informaion_Response>> response) {
                //정상적인 통신이 진행될 경우
                Log.d("통신 확인", response.toString());
                if (response.isSuccessful() || response.body() != null) {
                    ArrayList<Inquiry_of_Reservation_Informaion_Response> ior = response.body();

                    iorType_recycler = new Inquiry_of_Reservation_Information_recyclerAdapter(ior, getContext());
                    ior_list.setAdapter(iorType_recycler);
                } else {
                    // 오류 처리
                    Inquiry_of_Reservation_Informaion_Response errorObject = null;
                    ResponseBody rb = response.errorBody();
                    if (rb != null) {
                        try {
                            // 오류 응답을 문자열로 읽어옴
                            String errorResponse = rb.string();

                            // Gson을 사용하여 JSON 문자열을 JsonObject로 파싱
                            Gson gson = new Gson();
                            errorObject = gson.fromJson(errorResponse, Inquiry_of_Reservation_Informaion_Response.class);


                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (rb != null) {
                                rb.close(); // 반드시 닫아주어야 함
                            }
                        }


                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Inquiry_of_Reservation_Informaion_Response>> call, Throwable t) {

            }
        });

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
