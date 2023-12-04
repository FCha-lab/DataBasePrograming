package com.example.databaseprograming;

import android.content.Context;
import android.os.Bundle;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Medical_Records_Inquiry_Screen extends Fragment {


    //Screen Controller 변수 선언
    Screen_controller sc;

    //리사이클러뷰 - 진료과목별
    RecyclerView mri_list;
    Medical_Records_Inquiry_recyclerAdapter mriType_recycler;

    //검색창
    EditText search_bar;
    ImageButton search_button;

    //페이지 관련 위젯 변수 선언
    private ImageView page_back;

    //서버 관련 변수 선언
    Medical_Records_Inquiry_Retrofit_Client medical_records_inquiry_retrofit_client;

    public Medical_Records_Inquiry_Screen(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.medical_records_inquiry, container, false);

        //리사이클러뷰 연결
        mri_list = rootView.findViewById(R.id.mri_recycle);

        //리사이클러뷰 어댑터 초기화
        mri_list.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));

        //리사이클러뷰 어댑터 가로 스크롤바 없애기
        mri_list.setVerticalScrollBarEnabled(false);

        //위젯을 연결
        search_bar = rootView.findViewById(R.id.search_textview);
        search_button = rootView.findViewById(R.id.search_button);

        //뒤로가기 버튼 연결
        page_back = rootView.findViewById(R.id.page_back);

        //서버 관련 변수 초기화
        medical_records_inquiry_retrofit_client = new Medical_Records_Inquiry_Retrofit_Client();

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

        Medical_Records_Inquiry_Retrofit_Interface medical_records_inquiry_retrofit_interface = medical_records_inquiry_retrofit_client.getApiService(sc.getToken());

        medical_records_inquiry_retrofit_interface.getMRI().enqueue(new Callback<ArrayList<Medical_Records_Inquiry_Response>>() {
            @Override
            public void onResponse(Call<ArrayList<Medical_Records_Inquiry_Response>> call, Response<ArrayList<Medical_Records_Inquiry_Response>> response) {
                //정상적인 통신이 진행될 경우
                Log.d("통신 확인", response.toString());
                if (response.isSuccessful() || response.body() != null) {
                    ArrayList<Medical_Records_Inquiry_Response> mri = response.body();

                    mriType_recycler = new Medical_Records_Inquiry_recyclerAdapter(mri);
                    mri_list.setAdapter(mriType_recycler);
                } else {
                    // 오류 처리
                    Medical_Records_Inquiry_Response errorObject = null;
                    ResponseBody rb = response.errorBody();
                    if (rb != null) {
                        try {
                            // 오류 응답을 문자열로 읽어옴
                            String errorResponse = rb.string();

                            // Gson을 사용하여 JSON 문자열을 JsonObject로 파싱
                            Gson gson = new Gson();
                            errorObject = gson.fromJson(errorResponse, Medical_Records_Inquiry_Response.class);


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
            public void onFailure(Call<ArrayList<Medical_Records_Inquiry_Response>> call, Throwable t) {

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
