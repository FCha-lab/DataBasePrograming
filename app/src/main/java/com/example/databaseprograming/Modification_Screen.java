package com.example.databaseprograming;

import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.StringTokenizer;

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

    //비밀번호 보이기 관련 위젯 선언
    private ImageButton show_pw_button;

    //페이지 관련 변수 선언
    private boolean isShowedpw;

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
        isShowedpw = false;
        id_input.setEnabled(false);
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
        setArguments(null);


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


            }
        });

        show_pw_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //비밀번호 보이기 버튼 누르기
                if(isShowedpw){
                    //만약 비밀번호가 보이는 상태라면?
                    show_pw_button.setImageResource(R.drawable.closedeye);
                    pw_input.setInputType(android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    isShowedpw = false;
                }else{
                    //만약 비밀번호가 안 보이는 상태라면?
                    show_pw_button.setImageResource(R.drawable.openedeye);
                    pw_input.setInputType(android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    isShowedpw = true;
                }
            }
        });

        return rootView;
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
