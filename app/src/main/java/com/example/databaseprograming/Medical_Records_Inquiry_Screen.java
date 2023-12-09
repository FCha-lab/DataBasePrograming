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

public class Medical_Records_Inquiry_Screen extends Fragment {

    //Screen Controller 변수 선언
    private Screen_controller sc;

    //리사이클러뷰 - 진료과목별
    private RecyclerView mri_list;
    private Medical_Records_Inquiry_recyclerAdapter mriType_recycler;

    //검색창
    private EditText search_bar;
    private ImageButton search_button;

    //페이지 관련 위젯 변수 선언
    private ImageView page_back;

    //서버 관련 변수 선언
    Medical_Records_Inquiry_Retrofit_Client medical_records_inquiry_retrofit_client;

    //검색 기능
    private Callback<ArrayList<Medical_Records_Inquiry_Response>> search = new Callback<ArrayList<Medical_Records_Inquiry_Response>>() {
        @Override
        public void onResponse(Call<ArrayList<Medical_Records_Inquiry_Response>> call, Response<ArrayList<Medical_Records_Inquiry_Response>> response) {
            //정상적인 통신이 진행될 경우
            Log.d("통신 확인", response.toString());
            if (response.isSuccessful() || response.body() != null) {
                //정상적으로 토큰이 확인되었을 경우
                ArrayList<Medical_Records_Inquiry_Response> result = (ArrayList<Medical_Records_Inquiry_Response>) response.body();
                Log.d("통신 확인", result.toString());

                mriType_recycler = new Medical_Records_Inquiry_recyclerAdapter(result, sc);
                mri_list.setAdapter(mriType_recycler);

            } else {
                //토큰에 문제가 생겼을 경우
                //오류 정보를 받아오기
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

                    if (errorObject != null) {
                        Toast.makeText(sc.getApplicationContext(), "병원명을 한글로 입력하여 주십시오", Toast.LENGTH_SHORT).show();
                        // 오류 응답 처리
                    }
                }
            }
        }

        @Override
        public void onFailure(Call<ArrayList<Medical_Records_Inquiry_Response>> call, Throwable t) {
            //서버에 문제가 있을 경우
            Log.d("통신 확인", "onfailure 실패 ");
            ArrayList<Medical_Records_Inquiry_Response> item = new ArrayList<>();
            Medical_Records_Inquiry_Response daemi = new Medical_Records_Inquiry_Response();
            daemi.setRecordId(0);
            daemi.setRecordDate("Error");
            daemi.setDoctorName("Error");
            daemi.setHospitalName("서버에 문제가 발생했습니다.");

            item.add(daemi);

            mriType_recycler = new Medical_Records_Inquiry_recyclerAdapter(item, sc);
            mri_list.setAdapter(mriType_recycler);
        }
    };

    public Medical_Records_Inquiry_Screen() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.medical_records_inquiry, container, false);

        //리사이클러뷰 연결
        mri_list = rootView.findViewById(R.id.mri_recycle);

        //리사이클러뷰 어댑터 초기화
        mri_list.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

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
            public void onClick(View v) {
                //검색 버튼을 누를 경우
                Medical_Records_Inquiry_Retrofit_Interface r1 = medical_records_inquiry_retrofit_client.getApiService(sc.getToken());

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

        Medical_Records_Inquiry_Retrofit_Interface r1 = medical_records_inquiry_retrofit_client.getApiService(sc.getToken());

        r1.getMRI().enqueue(new Callback<ArrayList<Medical_Records_Inquiry_Response>>() {
            @Override
            public void onResponse(Call<ArrayList<Medical_Records_Inquiry_Response>> call, Response<ArrayList<Medical_Records_Inquiry_Response>> response) {
                //정상적인 통신이 진행될 경우
                Log.d("통신 확인", response.toString());
                if (response.isSuccessful() || response.body() != null) {
                    ArrayList<Medical_Records_Inquiry_Response> mri = response.body();

                    mriType_recycler = new Medical_Records_Inquiry_recyclerAdapter(mri, sc);
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
                //서버에 문제가 있을 경우
                Log.d("통신 확인", "onfailure 실패 ");
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
