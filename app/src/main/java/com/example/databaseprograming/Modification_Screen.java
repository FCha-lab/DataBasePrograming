package com.example.databaseprograming;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.StringTokenizer;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Modification_Screen extends Fragment {

    //Screen Controller 변수 선언
    private Screen_controller sc;

    //회원 정보 입력 위젯 선언
    private EditText id_input;
    private EditText pw_input;

    private EditText name_input;
    private EditText birthday_input;

    private EditText first_phone_input;
    private EditText middle_phone_input;
    private EditText last_phone_input;

    //버튼 위젯 선언
    private ImageButton page_back;
    private Button modification_button;
    private Button logout_button;

    //비밀번호 보이기 관련 위젯 선언
    private ImageButton show_pw_button;

    //입력 관련 설명 textview 변수 선언
    private TextView warning_pw;

    //서버 관련 변수 선언
    Modification_Request_RetrofitClient modification_request_retrofitClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.modification_screen, container, false);

        //위젯을 연결
        id_input = rootView.findViewById(R.id.id_input);
        pw_input = rootView.findViewById(R.id.pw_input);

        name_input = rootView.findViewById(R.id.name_input);
        birthday_input = rootView.findViewById(R.id.birthday_input);

        first_phone_input = rootView.findViewById(R.id.first_phone_input);
        middle_phone_input = rootView.findViewById(R.id.middle_phone_input);
        last_phone_input = rootView.findViewById(R.id.last_phone_input);

        page_back = rootView.findViewById(R.id.page_back);
        modification_button = rootView.findViewById(R.id.modification_button);
        logout_button = rootView.findViewById(R.id.logout_button);

        show_pw_button = rootView.findViewById(R.id.show_pw_button);

        warning_pw = rootView.findViewById(R.id.warning_pw);

        //공백 관련 필터 선언
        InputFilter filter[] =  new InputFilter[]{
                new NoSpaceInputFilter()
        };
        InputFilter[] phoneFilters = new InputFilter[]{
                new InputFilter.LengthFilter(4),
                new NoSpaceInputFilter()
        };
        InputFilter[] birthdayFilters = new InputFilter[]{
                new InputFilter.LengthFilter(8),
                new NoSpaceInputFilter()
        };

        //필터 등록
        id_input.setFilters(filter);
        pw_input.setFilters(filter);

        name_input.setFilters(filter);
        birthday_input.setFilters(birthdayFilters);

        first_phone_input.setFilters(phoneFilters);
        middle_phone_input.setFilters(phoneFilters);
        last_phone_input.setFilters(phoneFilters);

        //페이지 필요 변수 초기화
        id_input.setEnabled(false);

        //서버 관련 변수 초기화
        modification_request_retrofitClient = new Modification_Request_RetrofitClient();

        //버튼에 대한 리스너 등록
        page_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //상단 툴바 위로가기 버튼을 누를 경우
                sc.onBackPressed();
            }
        });

        modification_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //수정하기 버튼을 눌렀을 경우

                Modification_Request_RetrofitInterface r1 = modification_request_retrofitClient.getApiService(sc.getToken());
                //입력된 정보를 담기
                String pw = null;
                String name = null;
                String birthday = null;
                String phoneNumber = null;
                try {
                    pw = pw_input.getText().toString();
                    name = name_input.getText().toString();
                    birthday = birthday_input.getText().toString();
                    phoneNumber = first_phone_input.getText().toString() + "-" + middle_phone_input.getText().toString() + "-" + last_phone_input.getText().toString();
                }catch(Exception e){
                    Toast.makeText(sc.getApplicationContext(), "정보 입력 상황을 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //수정할 정보를 JSON객체에 실기
                UserInfo_Modification userInfo_modification = new UserInfo_Modification();
                userInfo_modification.setPassword(pw);
                userInfo_modification.setBirthDate(birthday);
                userInfo_modification.setPhoneNumber(phoneNumber);
                userInfo_modification.setUserName(name);

                r1.setUserInfo(userInfo_modification).enqueue(new Callback<UserInfo_Modification>() {
                    @Override
                    public void onResponse(Call<UserInfo_Modification> call, Response<UserInfo_Modification> response) {
                        //정상적인 통신이 진행될 경우
                        Log.d("통신 확인", response.toString());
                        if (response.isSuccessful() || response.body() != null) {
                            UserInfo_Modification result = response.body();
                            Log.d("통신 확인", result.toString());

                            Log.d("통신 확인", "회원정보 수정 완료!!!!" + result.toString());

                            Toast.makeText(sc.getApplicationContext(), "회원정보 수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                            sc.replaceFragment(new Main_Screen(), false);

                        } else {
                            //오류 처리
                            UserInfo_Modification errorObject = null;
                            ResponseBody rb = response.errorBody();
                            if (rb != null) {
                                try {
                                    // 오류 응답을 문자열로 읽어옴
                                    String errorResponse = rb.string();

                                    // Gson을 사용하여 JSON 문자열을 JsonObject로 파싱
                                    Gson gson = new Gson();
                                    errorObject = gson.fromJson(errorResponse, UserInfo_Modification.class);

                                    Log.d("통신 확인", "오류 응답: " + errorResponse);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } finally {
                                    if (rb != null) {
                                        rb.close(); // 반드시 닫아주어야 함
                                    }
                                }

                                if (errorObject != null) {
                                    // 오류 응답 처리
                                    Log.d("통신 확인", "오류 응답: " + errorObject.toString());
                                }
                                Toast.makeText(sc.getApplicationContext(), "실패 정보 : ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UserInfo_Modification> call, Throwable t) {
                        //서버에 문제가 있을 경우
                        Log.d("통신 확인", "onfailure 실패 ");
                    }
                });

            }
        });

        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(sc.getApplicationContext(), "로그아웃이 완료되었습니다!", Toast.LENGTH_SHORT).show();
                sc.setToken(null);
                sc.replaceFragment(new Main_Screen(),false);
            }
        });

        show_pw_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //비밀번호 보이기 버튼 누르기
                if (pw_input.getTransformationMethod() == HideReturnsTransformationMethod.getInstance()) {
                    //비밀번호 숨기기
                    show_pw_button.setImageResource(R.drawable.closedeye);
                    pw_input.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    //비밀번호 보이기
                    show_pw_button.setImageResource(R.drawable.openedeye);
                    pw_input.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                //출처: https://gooners0304.tistory.com/entry/EditText-비밀번호-보이기숨기기 [괴발개발 개발새발:티스토리]
            }
        });

        //비밀번호 입력 관련 설명
        pw_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() < 8){
                    //8자보다 적을 경우
                    warning_pw.setTextColor(Color.parseColor("#FF6E65"));
                }else {
                    //8자보다 많을 경우
                    warning_pw.setTextColor(Color.parseColor("#3F8CFF"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return rootView;
    }

    //edittext 초기화
    @Override
    public void onResume() {
        super.onResume();
        if (getArguments() != null)
        {
            String userName = getArguments().getString("userName"); // main 화면에서 받아온 값 넣기
            name_input.setText(userName);

            String userId = getArguments().getString("userId"); // main 화면에서 받아온 값 넣기
            id_input.setText(userId);

            String phoneNumber = getArguments().getString("phoneNumber"); // main 화면에서 받아온 값 넣기
            StringTokenizer phone = new StringTokenizer(phoneNumber, "-");
            first_phone_input.setText(phone.nextToken());
            middle_phone_input.setText(phone.nextToken());
            last_phone_input.setText(phone.nextToken());
            phone = null;

            String birthDate = getArguments().getString("birthDate"); // main 화면에서 받아온 값 넣기
            StringTokenizer birth = new StringTokenizer(birthDate, "-");
            birthDate = birth.nextToken() + birth.nextToken() + birth.nextToken();
            birthday_input.setText(birthDate);
            birth = null;

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
