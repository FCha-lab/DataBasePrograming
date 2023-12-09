package com.example.databaseprograming;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
    private Hospital_info_doctor_recyclerAdapter hDoctor_recycler;

    //상호작용 관련 위젯 변수 선언
    private ImageButton hospital_like;
    private Button reservation_button;
    private ImageButton page_back;

    //페이지 내 처리 변수 선언
    private boolean isHospitalLiked;
    private String hospital_id;

    //서버 관련 변수 선언
    private Hospital_RetrofitClient hospital_retrofitClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.hospital_info_screen, container, false);

        //리사이클러뷰 연결
        hospital_doctor_list = rootView.findViewById(R.id.hospital_doctor_recycle);

        //리사이클러뷰 어댑터 초기화
        hospital_doctor_list.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        //리사이클러뷰 어댑터 세로 스크롤바 없애기
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
        page_back = rootView.findViewById(R.id.page_back);

        //서버 관련 변수 초기화
        hospital_retrofitClient = new Hospital_RetrofitClient();


        //버튼에 대한 리스너 등록
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

        reservation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //검색 버튼을 눌렀을 경우
                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                //번들에 넘길 값 저장
                bundle.putString("hospitalId", hospital_id);
                Fragment target = sc.getScreen(new Reservation_Screen());//프래그먼트 선언
                target.setArguments(bundle);//번들을 프래그먼트로 보낼 준비

                sc.replaceFragment(new Reservation_Screen(), true);
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

        hospital_id = null;

        //페이지 열람시 전송된 정보 저장하기
        if (getArguments() != null) {
            // 페이지에 검색어 정보가 있을 경우
            // 병원 정보 받아 넣기
            hospital_id = getArguments().getString("id");

        } else {
            // 페이지에 검색어 정보가 없을 경우
            Toast.makeText(sc.getApplicationContext(), "병원 정보를 불러오는데 실패했습니다!", Toast.LENGTH_SHORT).show();
        }

        if (hospital_id != null) {
            Hospital_RetrofitInterface r1 = hospital_retrofitClient.getApiService(sc.getToken());

            r1.getHospitalInfo(hospital_id).enqueue(new Callback<Hospital_Info_Response>() {
                @Override
                public void onResponse(Call<Hospital_Info_Response> call, Response<Hospital_Info_Response> response) {
                    //정상적인 통신이 진행될 경우
                    Log.d("통신 확인", response.toString());
                    if (response.isSuccessful() || response.body() != null) {
                        //정상적으로 토큰이 확인되었을 경우
                        Hospital_Info_Response result = (Hospital_Info_Response) response.body();
                        Log.d("통신 확인", result.toString());

                        //병원 정보 입력
                        like_count.setText(String.valueOf(result.getLikesCount()));
                        hospital_open_date.setText(result.getOpenDate());
                        hospital_breaktime.setText(result.getBreakTime());

                        hospital_name.setText(result.getHospitalName());
                        hospital_type.setText(result.getDepartment());
                        hospital_opening_hour.setText(result.getOperatingHours());
                        hospital_phone_number.setText(result.getPhoneNumber());
                        hospital_adress.setText(result.getAddress());

                        //좋아요를 눌렀는지 확인
                        isHospitalLiked = result.isLikedByUser();
                        if (isHospitalLiked) {
                            //만약 이미 좋아요 상태라면?
                            hospital_like.setImageResource(R.drawable.heart_full);
                        } else {
                            //만약 좋아요를 누르지 않은 상태라면?
                            hospital_like.setImageResource(R.drawable.heart_empty);
                        }

                        //운영 상태 창 초기화
                        opening_hour.setText(result.getHospitalStatus());

                        //리사이클러뷰 초기화
                        hDoctor_recycler = new Hospital_info_doctor_recyclerAdapter(result.getDoctors());
                        hospital_doctor_list.setAdapter(hDoctor_recycler);

                    } else {
                        //토큰에 문제가 생겼을 경우
                        //오류 정보를 받아오기
                        Hospital_Info_Response errorObject = null;
                        ResponseBody rb = response.errorBody();
                        if (rb != null) {
                            try {
                                // 오류 응답을 문자열로 읽어옴
                                String errorResponse = rb.string();

                                // Gson을 사용하여 JSON 문자열을 JsonObject로 파싱
                                Gson gson = new Gson();
                                errorObject = gson.fromJson(errorResponse, Hospital_Info_Response.class);


                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                if (rb != null) {
                                    rb.close(); // 반드시 닫아주어야 함
                                }
                            }

                            if (errorObject != null) {
                                // 오류 응답 처리
                                hospital_type.setText("조회 에러");
                                hospital_name.setText("해당 병원이 존재하지 않습니다");

                                Toast.makeText(sc.getApplicationContext(), "조회 실패 : id-" + errorObject.getHospitalId() + ", 해당 아이디에 대한 정보 - " + errorObject.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<Hospital_Info_Response> call, Throwable t) {
                    //서버에 문제가 있을 경우
                    Log.d("통신 확인", "onfailure 실패 ");
                    ArrayList<Hospital_Info_Response.Doctor_Info> item = new ArrayList<>();
                    Hospital_Info_Response.Doctor_Info daemi = new Hospital_Info_Response.Doctor_Info();
                    daemi.setDoctorName("정보 없음");
                    daemi.setBiography("통신 에러");
                    daemi.setGender("M");

                    item.add(daemi);

                    hDoctor_recycler = new Hospital_info_doctor_recyclerAdapter(item);
                    hospital_doctor_list.setAdapter(hDoctor_recycler);
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