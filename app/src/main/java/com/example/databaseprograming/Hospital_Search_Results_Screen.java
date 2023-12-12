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
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Hospital_Search_Results_Screen extends Fragment {


    //Screen Controller 변수 선언
    private Screen_controller sc;

    //리사이클러뷰 - 진료과목별
    private RecyclerView hsr_list;
    private Hospital_Search_Results_recyclerAdapter hsrType_recycler;

    //검색창
    private EditText search_bar;
    private ImageButton search_button;

    //페이지 관련 위젯 변수 선언
    private ImageView page_back;

    //서버 관련 변수 선언
    private Hospital_RetrofitClient hospital_retrofitClient;
    private Callback<ArrayList<Hospital_Search_Result>> search = new Callback<ArrayList<Hospital_Search_Result>>() {
        @Override
        public void onResponse(Call<ArrayList<Hospital_Search_Result>> call, Response<ArrayList<Hospital_Search_Result>> response) {
            //정상적인 통신이 진행될 경우
            Log.d("통신 확인", response.toString());
            if (response.isSuccessful() || response.body() != null) {
                //정상적으로 토큰이 확인되었을 경우
                ArrayList<Hospital_Search_Result> result = (ArrayList<Hospital_Search_Result>) response.body();
                Log.d("통신 확인", result.toString());

                hsrType_recycler = new Hospital_Search_Results_recyclerAdapter(result, sc);
                hsr_list.setAdapter(hsrType_recycler);

            } else {
                //토큰에 문제가 생겼을 경우
                //오류 정보를 받아오기
                Hospital_Search_Result errorObject = null;
                ResponseBody rb = response.errorBody();
                if (rb != null) {
                    try {
                        // 오류 응답을 문자열로 읽어옴
                        String errorResponse = rb.string();

                        // Gson을 사용하여 JSON 문자열을 JsonObject로 파싱
                        Gson gson = new Gson();
                        errorObject = gson.fromJson(errorResponse, Hospital_Search_Result.class);


                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (rb != null) {
                            rb.close(); // 반드시 닫아주어야 함
                        }
                    }

                    if (errorObject != null) {
                        // 오류 응답 처리
                        ArrayList<Hospital_Search_Result> item = new ArrayList<>();
                        Hospital_Search_Result daemi = new Hospital_Search_Result();
                        daemi.setId("null");
                        daemi.setName("에러");
                        daemi.setDepartment("특수한 결과");
                        daemi.setOperatingHours("null");
                        daemi.setLikeCount(0);
                        daemi.setPhoneNumber("검색 에러");
                        daemi.setAddress("결과가 불확실합니다..");

                        item.add(daemi);

                        hsrType_recycler = new Hospital_Search_Results_recyclerAdapter(item, sc);
                        hsr_list.setAdapter(hsrType_recycler);
                    }
                }
            }
        }

        @Override
        public void onFailure(Call<ArrayList<Hospital_Search_Result>> call, Throwable t) {
            //서버에 문제가 있을 경우
            Log.d("통신 확인", "onfailure 실패 ");
            ArrayList<Hospital_Search_Result> item = new ArrayList<>();
            Hospital_Search_Result daemi = new Hospital_Search_Result();
            daemi.setId("null");
            daemi.setName("통신이 연결되지 않았습니다");
            daemi.setDepartment("통신 장애");
            daemi.setOperatingHours("null");
            daemi.setLikeCount(0);
            daemi.setPhoneNumber("통신 장애");
            daemi.setAddress("서버 연결 상황을 다시 확인해 주세요.");

            item.add(daemi);

            hsrType_recycler = new Hospital_Search_Results_recyclerAdapter(item, sc);
            hsr_list.setAdapter(hsrType_recycler);
        }
    };

    public Hospital_Search_Results_Screen() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.hospital_search_results, container, false);


        //서버 관련 변수 초기화
        hospital_retrofitClient = new Hospital_RetrofitClient();

        //리사이클러뷰 연결
        hsr_list = rootView.findViewById(R.id.hsr_recycle);

        //리사이클러뷰 어댑터 초기화
        hsr_list.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));


        //리사이클러뷰 어댑터 가로 스크롤바 없애기
        hsr_list.setVerticalScrollBarEnabled(false);

        //위젯을 연결
        search_bar = rootView.findViewById(R.id.search_textview);
        search_button = rootView.findViewById(R.id.search_button);

        //뒤로가기 버튼 연결
        page_back = rootView.findViewById(R.id.page_back);

        //버튼에 대한 리스너 등록
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //검색 버튼을 누를 경우
                Hospital_RetrofitInterface r1 = hospital_retrofitClient.getApiService(sc.getToken());

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
        
        //검색창 초기화
        search_bar.setText("");
        
        if (getArguments() != null) {
            // 페이지에 검색어 정보가 있을 경우
            // 검색 정보 받아 넣기
            String searchInfo = getArguments().getString("searchInfo");
            boolean isSearchBar = getArguments().getBoolean("isSearchBar");


            if (isSearchBar) {
                //만약 검색어를 통한 검색일 경우
                Hospital_RetrofitInterface r1 = hospital_retrofitClient.getApiService(sc.getToken());

                r1.getSearchResultByName(searchInfo).enqueue(search);

            } else {
                //만약 이미지 버튼(진료과목)을 통한 검색일 경우
                Hospital_RetrofitInterface r1 = hospital_retrofitClient.getApiService(sc.getToken());

                r1.getSearchResultByDepartment(searchInfo).enqueue(new Callback<ArrayList<Hospital_Search_Result>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Hospital_Search_Result>> call, Response<ArrayList<Hospital_Search_Result>> response) {
                        //정상적인 통신이 진행될 경우
                        Log.d("통신 확인", response.toString());
                        if (response.isSuccessful() || response.body() != null) {
                            //정상적으로 토큰이 확인되었을 경우
                            ArrayList<Hospital_Search_Result> result = (ArrayList<Hospital_Search_Result>) response.body();
                            Log.d("통신 확인", result.toString());

                            hsrType_recycler = new Hospital_Search_Results_recyclerAdapter(result, sc);
                            hsr_list.setAdapter(hsrType_recycler);

                        } else {
                            //토큰에 문제가 생겼을 경우
                            //오류 정보를 받아오기
                            Hospital_Search_Result errorObject = null;
                            ResponseBody rb = response.errorBody();
                            if (rb != null) {
                                try {
                                    // 오류 응답을 문자열로 읽어옴
                                    String errorResponse = rb.string();

                                    // Gson을 사용하여 JSON 문자열을 JsonObject로 파싱
                                    Gson gson = new Gson();
                                    errorObject = gson.fromJson(errorResponse, Hospital_Search_Result.class);


                                } catch (IOException e) {
                                    e.printStackTrace();
                                } finally {
                                    if (rb != null) {
                                        rb.close(); // 반드시 닫아주어야 함
                                    }
                                }

                                if (errorObject != null) {
                                    // 오류 응답 처리
                                    ArrayList<Hospital_Search_Result> item = new ArrayList<>();
                                    Hospital_Search_Result daemi = new Hospital_Search_Result();
                                    daemi.setId("null");
                                    daemi.setName("에러");
                                    daemi.setDepartment(daemi.getMessage());
                                    daemi.setOperatingHours("null");
                                    daemi.setLikeCount(0);
                                    daemi.setPhoneNumber("검색 에러");
                                    daemi.setAddress("입력 형식이 잘못되었습니다");

                                    item.add(daemi);
                                    Toast.makeText(sc.getApplicationContext(), "message:" + errorObject.getMessage(), Toast.LENGTH_SHORT).show();

                                    hsrType_recycler = new Hospital_Search_Results_recyclerAdapter(item, sc);
                                    hsr_list.setAdapter(hsrType_recycler);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Hospital_Search_Result>> call, Throwable t) {
                        //서버에 문제가 있을 경우
                        Log.d("통신 확인", "onfailure 실패 ");
                        ArrayList<Hospital_Search_Result> item = new ArrayList<>();
                        Hospital_Search_Result daemi = new Hospital_Search_Result();
                        daemi.setId("null");
                        daemi.setName("통신이 연결되지 않았습니다");
                        daemi.setDepartment("통신 장애");
                        daemi.setOperatingHours("null");
                        daemi.setLikeCount(0);
                        daemi.setPhoneNumber("통신 장애");
                        daemi.setAddress("서버 연결 상황을 다시 확인해 주세요.");

                        item.add(daemi);

                        hsrType_recycler = new Hospital_Search_Results_recyclerAdapter(item, sc);
                        hsr_list.setAdapter(hsrType_recycler);
                    }
                });

            }
        } else {
            // 페이지에 검색어 정보가 없을 경우
            Toast.makeText(sc.getApplicationContext(), "재검색을 해주세요.", Toast.LENGTH_SHORT).show();
        }
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
