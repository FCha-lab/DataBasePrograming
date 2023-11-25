package com.example.databaseprograming;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
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

import com.google.gson.Gson;

import java.io.IOException;
import java.util.StringTokenizer;

import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Screen extends Fragment {

    //Screen Controller 변수 선언
    private Screen_controller sc;

    //입력 관련 위젯 선언
    private TextView id_textview;
    private TextView pw_textview;
    private ImageButton show_pw_button;

    //로그인 조작 관련 위젯 선언
    private Button login_button;
    private TextView find_id;
    private TextView find_pw;
    private TextView join;

    //페이지 관련 변수 선언
    private boolean isShowedpw;

    //서버 관련 변수 선언
    private Login_RetrofitClient login_retrofitClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.login_screen, container, false);

        //위젯을 연결
        id_textview = rootView.findViewById(R.id.id_textview);
        pw_textview = rootView.findViewById(R.id.pw_textview);
        show_pw_button = rootView.findViewById(R.id.show_pw_button);

        login_button = rootView.findViewById(R.id.login_button);
//        find_id = rootView.findViewById(R.id.find_id);
//        find_pw = rootView.findViewById(R.id.find_pw);
        join = rootView.findViewById(R.id.join);

        //비밀번호 보이는지에 대한 선언
        isShowedpw = false;


        //서버 관련 처리
        Login_RetrofitInterface r1 = login_retrofitClient.getApiService();

        //버튼에 대한 리스너 등록
        show_pw_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //비밀번호 보이기 버튼 누르기
                if (isShowedpw) {
                    //만약 비밀번호가 보이는 상태라면?
                    show_pw_button.setImageResource(R.drawable.closedeye);
                    pw_textview.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    pw_textview.invalidate();
                    isShowedpw = false;
                } else {
                    //만약 비밀번호가 안 보이는 상태라면?
                    show_pw_button.setImageResource(R.drawable.openedeye);
                    pw_textview.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    pw_textview.invalidate();
                    isShowedpw = true;
                }
            }
        });


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //로그인 버튼을 눌렀다면?
                //로그인 시도 정보를 객체로 저장
                String id = id_textview.getText().toString();
                String pw = pw_textview.getText().toString();

                //공백 지우기
                id = id.trim();
                pw = pw.trim();

                //JSON 객체를 생성하고 입력된 정보를 저장
                Login_Request loginRequest = new Login_Request(id, pw);

                //통신 시도
                r1.getLoginResponse(loginRequest).enqueue(new Callback<Login_Response>() {
                    @Override
                    public void onResponse(Call<Login_Response> call, Response<Login_Response> response) {
                        //정상적인 통신이 진행될 경우
                        Log.d("통신 확인", response.toString());
                        if (response.isSuccessful() || response.body() != null) {
                            Login_Response result = response.body();
                            Log.d("통신 확인", result.toString());

                            Log.d("통신 확인", "로그인 정보 확인 완료!!!!" + result.toString());
                            Headers headers = response.headers();
                            StringTokenizer authorization = new StringTokenizer(headers.get("Authorization"), " ");
                            String token = authorization.nextToken();
                            token = authorization.nextToken();

                            sc.setToken(token);
                            sc.replaceFragment(sc.getScreen(new Main_Screen()));

                        } else {
                            // 401 오류 처리
                            Login_Response errorObject = null;
                            ResponseBody rb = response.errorBody();
                            if (rb != null) {
                                try {
                                    // 오류 응답을 문자열로 읽어옴
                                    String errorResponse = rb.string();

                                    // Gson을 사용하여 JSON 문자열을 JsonObject로 파싱
                                    Gson gson = new Gson();
                                    errorObject = gson.fromJson(errorResponse, Login_Response.class);


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
                                Toast.makeText(sc.getApplicationContext(), "아이디와 비밀번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Login_Response> call, Throwable t) {
                        //서버에 문제가 있을 경우
                        Log.d("통신 확인", "onfailure 실패 ");
                    }
                });

            }
        });

        /*find_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        find_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //회원가입 텍스트를 눌렀을 경우
                sc.replaceFragment(new Join_Screen());
            }
        });


        return rootView;
    }

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