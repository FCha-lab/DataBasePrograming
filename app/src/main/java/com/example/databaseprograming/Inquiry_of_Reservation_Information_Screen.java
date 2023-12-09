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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class Inquiry_of_Reservation_Information_Screen extends Fragment {


    //Screen Controller 변수 선언
    Screen_controller sc;

    //리사이클러뷰 - 진료과목별
    RecyclerView ior_list;
    Inquiry_of_Reservation_Information_recyclerAdapter iorType_recycler;

    //검색창
    EditText search_bar;
    ImageButton search_button;

    //페이지 관련 위젯 변수 선언
    private ImageView page_back;

    //서버 관련 변수 선언
    Reservation_RetrofitClient inquiry_of_reservation_information_retrofit_client;

    //검색 기능
    private Callback<ArrayList<Inquiry_of_Reservation_Information_Response>> search = new Callback<ArrayList<Inquiry_of_Reservation_Information_Response>>() {
        @Override
        public void onResponse(Call<ArrayList<Inquiry_of_Reservation_Information_Response>> call, Response<ArrayList<Inquiry_of_Reservation_Information_Response>> response) {
            //정상적인 통신이 진행될 경우
            Log.d("통신 확인", response.toString());
            if (response.isSuccessful() || response.body() != null) {
                //정상적으로 토큰이 확인되었을 경우
                ArrayList<Inquiry_of_Reservation_Information_Response> result = (ArrayList<Inquiry_of_Reservation_Information_Response>) response.body();
                Log.d("통신 확인", result.toString());

                iorType_recycler = new Inquiry_of_Reservation_Information_recyclerAdapter(result, getContext(), sc);
                ior_list.setAdapter(iorType_recycler);

            } else {
                //토큰에 문제가 생겼을 경우
                //오류 정보를 받아오기
                Inquiry_of_Reservation_Information_Response errorObject = null;
                ResponseBody rb = response.errorBody();
                if (rb != null) {
                    try {
                        // 오류 응답을 문자열로 읽어옴
                        String errorResponse = rb.string();

                        // Gson을 사용하여 JSON 문자열을 JsonObject로 파싱
                        Gson gson = new Gson();
                        errorObject = gson.fromJson(errorResponse, Inquiry_of_Reservation_Information_Response.class);


                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (rb != null) {
                            rb.close(); // 반드시 닫아주어야 함
                        }
                    }

                    if (errorObject != null) {
                        Toast.makeText(sc.getApplicationContext(), "병원명을 한글로 입력하여 주십시오", Toast.LENGTH_SHORT).show();
                        // 오류 응답 처리
                    }
                }
            }
        }

        @Override
        public void onFailure(Call<ArrayList<Inquiry_of_Reservation_Information_Response>> call, Throwable t) {
            //서버에 문제가 있을 경우
            Log.d("통신 확인", "onfailure 실패 ");
            ArrayList<Inquiry_of_Reservation_Information_Response> item = new ArrayList<>();
            Inquiry_of_Reservation_Information_Response daemi = new Inquiry_of_Reservation_Information_Response();
            daemi.setAppointmentStatus("오류");
            daemi.setHospitalName("Error");
            daemi.setHospitalAddress("Error");
            daemi.setDate("서버에 문제가 발생했습니다.");
            daemi.setTime("서버에 문제가 발생했습니다.");

            item.add(daemi);

            iorType_recycler = new Inquiry_of_Reservation_Information_recyclerAdapter(item, getContext(), sc);
            ior_list.setAdapter(iorType_recycler);
        }
    };
    public Inquiry_of_Reservation_Information_Screen(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.inquiry_of_reservation_information, container, false);

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
        inquiry_of_reservation_information_retrofit_client = new Reservation_RetrofitClient();

        //버튼에 대한 리스너 등록
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //검색 버튼을 누를 경우
                Reservation_RetrofitInterface r1 = inquiry_of_reservation_information_retrofit_client.getApiService(sc.getToken());

                r1.getSearchResultByName(search_bar.getText().toString()).enqueue(search);

            }
        });

        //상단 툴바 뒤로가기 버튼을 누를 경우
        page_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sc.onBackPressed();
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        //검색창 초기화
        search_bar.setText("");

        Reservation_RetrofitInterface inquiry_of_reservation_information_retrofit_interface = inquiry_of_reservation_information_retrofit_client.getApiService(sc.getToken());

        inquiry_of_reservation_information_retrofit_interface.getIOR().enqueue(new Callback<ArrayList<Inquiry_of_Reservation_Information_Response>>() {
            @Override
            public void onResponse(Call<ArrayList<Inquiry_of_Reservation_Information_Response>> call, Response<ArrayList<Inquiry_of_Reservation_Information_Response>> response) {
                //정상적인 통신이 진행될 경우
                Log.d("통신 확인", response.toString());
                if (response.isSuccessful() || response.body() != null) {
                    ArrayList<Inquiry_of_Reservation_Information_Response> ior = response.body();

                    iorType_recycler = new Inquiry_of_Reservation_Information_recyclerAdapter(ior, getContext(),sc);
                    ior_list.setAdapter(iorType_recycler);
                } else {
                    // 오류 처리
                    Inquiry_of_Reservation_Information_Response errorObject = null;
                    ResponseBody rb = response.errorBody();
                    if (rb != null) {
                        try {
                            // 오류 응답을 문자열로 읽어옴
                            String errorResponse = rb.string();

                            // Gson을 사용하여 JSON 문자열을 JsonObject로 파싱
                            Gson gson = new Gson();
                            errorObject = gson.fromJson(errorResponse, Inquiry_of_Reservation_Information_Response.class);


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
            public void onFailure(Call<ArrayList<Inquiry_of_Reservation_Information_Response>> call, Throwable t) {

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
