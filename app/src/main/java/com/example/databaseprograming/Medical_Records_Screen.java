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

    //서버 관련 변수 선언
    private Medical_Records_Inquiry_Retrofit_Client medical_records_inquiry_retrofit_client;
    private Hospital_RetrofitClient hospital_retrofitClient;

    //상호작용 관련 위젯 변수 선언
    private ImageButton hospital_like;
    private ImageButton page_back;

    //페이지 내 처리 변수 선언
    private boolean isHospitalLiked;
    private int record_id;
    private String hospital_id;

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

        //서버 관련 변수 초기화
        medical_records_inquiry_retrofit_client = new Medical_Records_Inquiry_Retrofit_Client();
        hospital_retrofitClient = new Hospital_RetrofitClient();

        //상단 툴바 뒤로가기 버튼을 누를 경우
        page_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sc.onBackPressed();
            }
        });

        //좋아요 버튼에 대한 리스너 등록
        hospital_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //좋아요 버튼을 눌렀을 때
                if(hospital_id.equals(null)){
                    Toast.makeText(sc.getApplicationContext(), "에러 : 병원 정보가 유실되었습니다. 페이지를 다시 열어주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (isHospitalLiked) {
                    //만약 이미 좋아요 상태라면?
                    Hospital_RetrofitInterface r1 = hospital_retrofitClient.getApiService(sc.getToken());

                    r1.setLikeCancel(hospital_id).enqueue(new Callback<Like_Info>() {
                        @Override
                        public void onResponse(Call<Like_Info> call, Response<Like_Info> response) {
                            //정상적인 통신이 진행될 경우
                            Log.d("통신 확인", response.toString());
                            if (response.isSuccessful() || response.body() != null) {
                                //정상적으로 토큰이 확인되었을 경우
                                Like_Info result = (Like_Info) response.body();

                                //좋아요 취소 수행
                                like_count.setText(String.valueOf(result.getTotal()));
                                hospital_like.setImageResource(R.drawable.heart_empty);
                                isHospitalLiked = false;

                            } else {
                                //토큰에 문제가 생겼을 경우
                                //오류 정보를 받아오기
                                Like_Info errorObject = null;
                                ResponseBody rb = response.errorBody();
                                if (rb != null) {
                                    try {
                                        // 오류 응답을 문자열로 읽어옴
                                        String errorResponse = rb.string();

                                        // Gson을 사용하여 JSON 문자열을 JsonObject로 파싱
                                        Gson gson = new Gson();
                                        errorObject = gson.fromJson(errorResponse, Like_Info.class);


                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } finally {
                                        if (rb != null) {
                                            rb.close(); // 반드시 닫아주어야 함
                                        }
                                    }

                                    if (errorObject != null) {
                                        // 오류 응답 처리

                                        if (response.code() == 401) {
                                            //로그인 관련 에러일 경우
                                            if(errorObject.getError() == null){
                                                Toast.makeText(sc.getApplicationContext(), "로그인 만료! 다시 로그인 해주세요! message:" + errorObject.getMessage(), Toast.LENGTH_SHORT).show();
                                                sc.replaceFragment(new Login_Screen(), true);
                                            }else{
                                                Toast.makeText(sc.getApplicationContext(), errorObject.getError() + ", 로그인을 해주세요!", Toast.LENGTH_SHORT).show();
                                                sc.replaceFragment(new Login_Screen(), true);
                                            }

                                        } else {
                                            //기타 에러
                                            Toast.makeText(sc.getApplicationContext(), "취소 실패 : 상태 번호 : " + errorObject.getStatus() + ", message : " + errorObject.getMessage(), Toast.LENGTH_SHORT).show();
                                        }

                                        Log.d("좋아요 통신 테스트", errorObject.getMessage());


                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Like_Info> call, Throwable t) {
                            //서버에 문제가 있을 경우
                            Log.d("통신 확인", "onfailure 실패 ");
                            Toast.makeText(sc.getApplicationContext(), "취소 실패 : 서버 상태를 확인해주세요", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    //만약 좋아요를 누르지 않은 상태였다면?
                    Hospital_RetrofitInterface r1 = hospital_retrofitClient.getApiService(sc.getToken());

                    Like_Info info = new Like_Info();
                    info.setHospitalId(hospital_id);

                    r1.setLikeAdd(info).enqueue(new Callback<Like_Info>() {
                        @Override
                        public void onResponse(Call<Like_Info> call, Response<Like_Info> response) {
                            //정상적인 통신이 진행될 경우
                            Log.d("통신 확인", response.toString());
                            if (response.isSuccessful() || response.body() != null) {
                                //정상적으로 토큰이 확인되었을 경우
                                Like_Info result = (Like_Info) response.body();

                                //좋아요 취소 수행
                                like_count.setText(String.valueOf(result.getTotal()));
                                hospital_like.setImageResource(R.drawable.heart_full);
                                isHospitalLiked = true;

                            } else {
                                //토큰에 문제가 생겼을 경우
                                //오류 정보를 받아오기
                                Like_Info errorObject = null;
                                ResponseBody rb = response.errorBody();
                                if (rb != null) {
                                    try {
                                        // 오류 응답을 문자열로 읽어옴
                                        String errorResponse = rb.string();


                                        Log.d("좋아요 통신 테스트", "errorbody : " + errorResponse);

                                        // Gson을 사용하여 JSON 문자열을 JsonObject로 파싱
                                        Gson gson = new Gson();
                                        errorObject = gson.fromJson(errorResponse, Like_Info.class);


                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } finally {
                                        if (rb != null) {
                                            rb.close(); // 반드시 닫아주어야 함
                                        }
                                    }

                                    if (errorObject != null) {
                                        // 오류 응답 처리

                                        if (response.code() == 401) {
                                            //로그인 관련 에러일 경우
                                            if(errorObject.getError() == null){
                                                Toast.makeText(sc.getApplicationContext(), "로그인 만료! 다시 로그인 해주세요! message:" + errorObject.getMessage(), Toast.LENGTH_SHORT).show();
                                                sc.replaceFragment(new Login_Screen(), true);
                                            }else{
                                                Toast.makeText(sc.getApplicationContext(), errorObject.getError() + ", 로그인을 해주세요!", Toast.LENGTH_SHORT).show();
                                                sc.replaceFragment(new Login_Screen(), true);
                                            }
                                        } else {
                                            //기타 에러
                                            Toast.makeText(sc.getApplicationContext(), "좋아요 실패 : 상태 번호 : " + errorObject.getStatus() + ", message : " + errorObject.getMessage(), Toast.LENGTH_SHORT).show();
                                        }

                                        Log.d("좋아요 통신 테스트", errorObject.getMessage());
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Like_Info> call, Throwable t) {
                            //서버에 문제가 있을 경우
                            Log.d("통신 확인", "onfailure 실패 ");
                            Toast.makeText(sc.getApplicationContext(), "좋아요 실패 : 서버 상태를 확인해주세요", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        page_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setArguments(null);
                sc.onBackPressed();
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        //위젯의 정보 초기화
        user_birthdate.setText("");
        user_name.setText("");
        medical_record_date.setText("");
        hospital_name.setText("");

        hospital_phone_number.setText("");
        like_count.setText("");
        hospital_doctor_name.setText("");
        hospital_doctor_license.setText("");
        doctor_opinion.setText("");

        //페이지 내 처리 변수 초기화
        isHospitalLiked = false;

        record_id = 0;

        //페이지 열람시 전송된 정보 저장하기
        if (getArguments() != null) {
            // 페이지에 검색어 정보가 있을 경우
            // 병원 정보 받아 넣기
            record_id = getArguments().getInt("record_id");

        } else {
            // 페이지에 검색어 정보가 없을 경우
            Toast.makeText(sc.getApplicationContext(), "진료 기록을 불러오는데 실패했습니다!", Toast.LENGTH_SHORT).show();
        }

        if(record_id != 0){
            Medical_Records_Inquiry_Retrofit_Interface r1 = medical_records_inquiry_retrofit_client.getApiService(sc.getToken());

            r1.getMRD(record_id).enqueue(new Callback<Medical_Records_Detail_Response>() {
                @Override
                public void onResponse(Call<Medical_Records_Detail_Response> call, Response<Medical_Records_Detail_Response> response) {
                    //정상적인 통신이 진행될 경우
                    Log.d("통신 확인", response.toString());
                    if (response.isSuccessful() || response.body() != null) {
                        //정상적으로 토큰이 확인되었을 경우
                        Medical_Records_Detail_Response result = (Medical_Records_Detail_Response) response.body();
                        Log.d("통신 확인", result.toString());

                        //병원 정보 입력
                        user_birthdate.setText(result.getBirthDate());
                        user_name.setText(result.getPatientName());
                        medical_record_date.setText(result.getRecordDate());
                        hospital_name.setText(result.getHospitalName());
                        hospital_phone_number.setText(result.getPhoneNumber());
                        like_count.setText(String.valueOf(result.getCountLikes()));
                        hospital_doctor_name.setText(result.getDoctorName());
                        hospital_doctor_license.setText(result.getLicenseNumber());
                        doctor_opinion.setText(result.getContent());

                        //좋아요를 눌렀는지 확인
                        isHospitalLiked = result.isLikes();
                        if (isHospitalLiked) {
                            //만약 이미 좋아요 상태라면?
                            hospital_like.setImageResource(R.drawable.heart_full);
                        } else {
                            //만약 좋아요를 누르지 않은 상태라면?
                            hospital_like.setImageResource(R.drawable.heart_empty);
                        }

//                        //운영 상태 창 초기화
//                        opening_hour.setText(result.getHospitalStatus());

                    } else {
                        //토큰에 문제가 생겼을 경우
                        //오류 정보를 받아오기
                        Medical_Records_Detail_Response errorObject = null;
                        ResponseBody rb = response.errorBody();
                        if (rb != null) {
                            try {
                                // 오류 응답을 문자열로 읽어옴
                                String errorResponse = rb.string();

                                // Gson을 사용하여 JSON 문자열을 JsonObject로 파싱
                                Gson gson = new Gson();
                                errorObject = gson.fromJson(errorResponse, Medical_Records_Detail_Response.class);


                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                if (rb != null) {
                                    rb.close(); // 반드시 닫아주어야 함
                                }
                            }

                            if (errorObject != null) {
                                // 오류 응답 처리
                                hospital_name.setText("해당 병원이 존재하지 않습니다");

                                Toast.makeText(sc.getApplicationContext(), "조회 실패 : record_id-"+ errorObject.getRecordId() + ", 해당 아이디에 대한 정보 - " + errorObject.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<Medical_Records_Detail_Response> call, Throwable t) {
                    //서버에 문제가 있을 경우
                    Log.d("통신 확인", "onfailure 실패 ");
                }
            });
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