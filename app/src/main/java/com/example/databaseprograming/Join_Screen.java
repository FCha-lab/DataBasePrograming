package com.example.databaseprograming;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

    //페이지 관련 변수 선언
    private boolean isShowedpw;

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


        //페이지 필요 변수 초기화
        isShowedpw = false;

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
