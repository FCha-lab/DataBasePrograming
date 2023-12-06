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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private String hospital_id;

    //서버 관련 변수 선언
    private Reservation_Available_Time_RetrofitClient reservation_available_time_retrofitClient;
    private Reservation_RetrofitClient reservation_retrofitClient;
    private retrofit2.Callback<Reservation_Available_Time_Response> check_time = new Callback<Reservation_Available_Time_Response>() {
        @Override
        public void onResponse(Call<Reservation_Available_Time_Response> call, Response<Reservation_Available_Time_Response> response) {
            //정상적인 통신이 진행될 경우
            Log.d("통신 확인", response.toString());
            if (response.isSuccessful() || response.body() != null) {
                //정상적으로 토큰이 확인되었을 경우
                Reservation_Available_Time_Response result = (Reservation_Available_Time_Response) response.body();
                Log.d("통신 확인", result.toString());

                //병원 이름 설정
                hospital_name.setText(result.getHospitalName());

                //버튼 비활성화 여부 확인
                for(int i=0; i<buttonList.length;i++){
                    for(int j=0;j<result.getAvailableTime().size();j++){
                        if(buttonList[i].getText().toString().equals(result.getAvailableTime().get(j).getTime())){
                            if(result.getAvailableTime().get(j).getAvailableSlots() != 0){
                                buttonList[i].setEnabled(true);
                            }
                            break;
                        }
                    }
                }

            } else {
                //토큰에 문제가 생겼을 경우
                //오류 정보를 받아오기
                Reservation_Available_Time_Response errorObject = null;
                ResponseBody rb = response.errorBody();
                if (rb != null) {
                    try {
                        // 오류 응답을 문자열로 읽어옴
                        String errorResponse = rb.string();

                        // Gson을 사용하여 JSON 문자열을 JsonObject로 파싱
                        Gson gson = new Gson();
                        errorObject = gson.fromJson(errorResponse, Reservation_Available_Time_Response.class);


                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (rb != null) {
                            rb.close(); // 반드시 닫아주어야 함
                        }
                    }

                    if (errorObject != null) {
                        // 오류 응답 처리
                        Toast.makeText(sc.getApplicationContext(), "조회 실패 : name-"+ errorObject.getHospitalName() + ", 해당 병원에 대한 정보 - " + errorObject.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }
        }

        @Override
        public void onFailure(Call<Reservation_Available_Time_Response> call, Throwable t) {
            //서버에 문제가 있을 경우
            Log.d("통신 확인", "onfailure 실패 ");
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.reservation_screen, container, false);


        //위젯을 연결
        hospital_name = rootView.findViewById(R.id.hospital_name);

        select_date =  rootView.findViewById(R.id.select_date);

        buttonList = new RadioButton[15];
        buttonList[0] = rootView.findViewById(R.id.am1);
        buttonList[1] = rootView.findViewById(R.id.am2);
        buttonList[2] = rootView.findViewById(R.id.am3);
        buttonList[3] = rootView.findViewById(R.id.am4);
        buttonList[4] = rootView.findViewById(R.id.pm1);
        buttonList[5] = rootView.findViewById(R.id.pm2);
        buttonList[6] = rootView.findViewById(R.id.pm3);
        buttonList[7] = rootView.findViewById(R.id.pm4);
        buttonList[8] = rootView.findViewById(R.id.pm5);
        buttonList[9] = rootView.findViewById(R.id.pm6);
        buttonList[10] = rootView.findViewById(R.id.pm7);
        buttonList[11] = rootView.findViewById(R.id.pm8);
        buttonList[12] = rootView.findViewById(R.id.pm9);
        buttonList[13] = rootView.findViewById(R.id.pm10);
        buttonList[14] = rootView.findViewById(R.id.pm11);

        reservation_button = rootView.findViewById(R.id.reservation_button);

        page_back = rootView.findViewById(R.id.page_back);

        //페이지 관련 변수 초기화
        current_button = null;
        hospital_name.setText("병원 이름");

        //서버 관련 변수 초기화
        reservation_available_time_retrofitClient = new Reservation_Available_Time_RetrofitClient();
        reservation_retrofitClient = new Reservation_RetrofitClient();


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
                if(current_button == null){
                    //선택한 버튼이 없을 경우
                    Toast.makeText(sc.getApplicationContext(), "시간을 선택해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 선택된 날짜로 Calendar 객체 생성
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.setTimeInMillis(select_date.getDate());
                // 날짜 포맷 지정
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String date = sdf.format(selectedDate.getTime());

                Reservation_Request body = new Reservation_Request();
                body.setDate(date);
                body.setTime(current_button.getText().toString());
                body.setHospitalId(hospital_id);

                Reservation_RetrofitInterface r1 = reservation_retrofitClient.getApiService(sc.getToken());

                r1.setReservation(body).enqueue(new Callback<Reservation_Response>() {
                    @Override
                    public void onResponse(Call<Reservation_Response> call, Response<Reservation_Response> response) {
                        //정상적인 통신이 진행될 경우
                        Log.d("통신 확인", response.toString());
                        if (response.isSuccessful() || response.body() != null) {
                            //정상적으로 토큰이 확인되었을 경우
                            Reservation_Response result = (Reservation_Response) response.body();
                            Log.d("통신 확인", result.toString());

                            Toast.makeText(sc.getApplicationContext(), result.getDate() + " " + result.getTime() + " " + hospital_name.getText().toString() + " 예약되었습니다!", Toast.LENGTH_SHORT).show();

                            sc.replaceFragment(new Hospital_Info_Screen(), true);
                        } else {
                            //토큰에 문제가 생겼을 경우
                            //오류 정보를 받아오기
                            Reservation_Response errorObject = null;
                            ResponseBody rb = response.errorBody();
                            if (rb != null) {
                                try {
                                    // 오류 응답을 문자열로 읽어옴
                                    String errorResponse = rb.string();

                                    // Gson을 사용하여 JSON 문자열을 JsonObject로 파싱
                                    Gson gson = new Gson();
                                    errorObject = gson.fromJson(errorResponse, Reservation_Response.class);


                                } catch (IOException e) {
                                    e.printStackTrace();
                                } finally {
                                    if (rb != null) {
                                        rb.close(); // 반드시 닫아주어야 함
                                    }
                                }

                                if (errorObject != null) {
                                    // 오류 응답 처리
                                    int status = response.code();
                                    if(status == 401){
                                        if(errorObject.getError() == null){
                                            Toast.makeText(sc.getApplicationContext(), "로그인 만료! 다시 로그인 해주세요! message:" + errorObject.getMessage(), Toast.LENGTH_SHORT).show();
                                            sc.replaceFragment(new Login_Screen(), true);
                                        }else{
                                            Toast.makeText(sc.getApplicationContext(), errorObject.getError() + ", 로그인을 해주세요!", Toast.LENGTH_SHORT).show();
                                            sc.replaceFragment(new Login_Screen(), true);
                                        }
                                    }else{
                                        Toast.makeText(sc.getApplicationContext(), "status:"+errorObject.getStatus() +", message:" +errorObject.getMessage(), Toast.LENGTH_SHORT).show();
                                    }


                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Reservation_Response> call, Throwable t) {
                        //서버에 문제가 있을 경우
                        Log.d("통신 확인", "onfailure 실패 ");
                    }
                });

            }
        });

        //캘린더 뷰에 대한 리스너 등록
        select_date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //페이지 관련 변수 초기화
                if(current_button != null){
                    current_button.setChecked(false);
                    current_button = null;
                }

                //라디오버튼 전체 비활성화
                for(int i =0 ;i<buttonList.length;i++){
                    buttonList[i].setEnabled(false);
                }

                // 선택된 날짜로 Calendar 객체 생성
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);

                // 날짜 포맷 지정
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

                // 포맷된 날짜 문자열 가져오기
                String date = sdf.format(selectedDate.getTime());

                Reservation_Available_Time_RetrofitInterface r1 = reservation_available_time_retrofitClient.getApiService();

                r1.getAvailableTime(hospital_id, date).enqueue(check_time);


            }
        });

        return  rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        //페이지 관련 변수 초기화
        current_button = null;
        hospital_id = null;

        //라디오버튼 전체 비활성화
        for(int i =0 ;i<buttonList.length;i++){
            buttonList[i].setChecked(false);
            buttonList[i].setEnabled(false);
        }

        if(getArguments() != null){
            hospital_id = getArguments().getString("hospitalId");
        }

        if(hospital_id != null){
            Reservation_Available_Time_RetrofitInterface r1 = reservation_available_time_retrofitClient.getApiService();
            // 선택된 날짜로 Calendar 객체 생성
            Calendar selectedDate = Calendar.getInstance();
            selectedDate.setTimeInMillis(select_date.getDate());
            // 날짜 포맷 지정
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String date = sdf.format(selectedDate.getTime());

            r1.getAvailableTime(hospital_id, date).enqueue(check_time);
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
