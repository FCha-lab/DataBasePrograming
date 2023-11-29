package com.example.databaseprograming;

import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Join_Screen extends Fragment {

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
    private Button check_duplicate;
    private Button join_button;

    //비밀번호 보이기 관련 위젯 선언
    private ImageButton show_pw_button;


    //서버 관련 변수 선언
    private Join_RetrofitClient join_retrofitClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.join_screen, container, false);

        //위젯을 연결
        id_input = rootView.findViewById(R.id.id_input);
        pw_input = rootView.findViewById(R.id.pw_input);

        name_input = rootView.findViewById(R.id.name_input);
        birthday_input = rootView.findViewById(R.id.birthday_input);

        first_phone_input = rootView.findViewById(R.id.first_phone_input);
        middle_phone_input = rootView.findViewById(R.id.middle_phone_input);
        last_phone_input = rootView.findViewById(R.id.last_phone_input);

        page_back = rootView.findViewById(R.id.page_back);
        join_button = rootView.findViewById(R.id.join_button);
        check_duplicate = rootView.findViewById(R.id.check_duplicate);

        show_pw_button = rootView.findViewById(R.id.show_pw_button);

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

        //서버 관련 처리
        join_retrofitClient = new Join_RetrofitClient();

        //버튼에 대한 리스너 등록
        page_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sc.onBackPressed();
            }
        });

        join_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //회원가입 버튼을 누를 경우

                Join_RetrofitInterface r1 = join_retrofitClient.getApiService();
                //입력된 회원가입 정보를 저장
                String id = null;
                String pw = null;
                String name = null;
                String birthday = null;
                String phoneNumber = null;
                try {
                    id = id_input.getText().toString();
                    pw = pw_input.getText().toString();
                    name = name_input.getText().toString();
                    birthday = birthday_input.getText().toString();
                    phoneNumber = first_phone_input.getText().toString() + "-" + middle_phone_input.getText().toString() + "-" + last_phone_input.getText().toString();
                }catch(Exception e){
                    Toast.makeText(sc.getApplicationContext(), "정보 입력 상황을 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //JSON 객체를 생성하고 입력된 정보를 저장
                Join_Request joinRequest = new Join_Request();
                joinRequest.setuserId(id);
                joinRequest.setpassword(pw);
                joinRequest.setuserName(name);
                joinRequest.setbirthDate(birthday);
                joinRequest.setphoneNumber(phoneNumber);

                //통신 시도
                r1.getJoinResponse(joinRequest).enqueue(new Callback<Join_Response>() {
                    @Override
                    public void onResponse(Call<Join_Response> call, Response<Join_Response> response) {
                        //정상적인 통신이 진행될 경우
                        Log.d("통신 확인", response.toString());
                        if (response.isSuccessful() || response.body() != null) {
                            Join_Response result = response.body();
                            Log.d("통신 확인", result.toString());

                            Log.d("통신 확인", "회원가입 확인 완료!!!!" + result.toString());

                            Toast.makeText(sc.getApplicationContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                            sc.replaceFragment(new Login_Screen());

                        } else {
                            //오류 처리
                            Join_Response errorObject = null;
                            ResponseBody rb = response.errorBody();
                            if (rb != null) {
                                try {
                                    // 오류 응답을 문자열로 읽어옴
                                    String errorResponse = rb.string();

                                    // Gson을 사용하여 JSON 문자열을 JsonObject로 파싱
                                    Gson gson = new Gson();
                                    errorObject = gson.fromJson(errorResponse, Join_Response.class);

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
                                Toast.makeText(sc.getApplicationContext(), errorObject.getStatus() + ", " + errorObject.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Join_Response> call, Throwable t) {
                        //서버에 문제가 있을 경우
                        Log.d("통신 확인", "onfailure 실패 ");
                    }
                });
            }
        });

        check_duplicate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
