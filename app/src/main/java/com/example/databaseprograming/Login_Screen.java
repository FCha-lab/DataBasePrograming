package com.example.databaseprograming;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.login_screen, container, false);

        //위젯을 연결
        id_textview = rootView.findViewById(R.id.id_textview);
        pw_textview = rootView.findViewById(R.id.pw_textview);
        show_pw_button = rootView.findViewById(R.id.show_pw_button);

        login_button = rootView.findViewById(R.id.login_button);
        find_id = rootView.findViewById(R.id.find_id);
        find_pw = rootView.findViewById(R.id.find_pw);
        join = rootView.findViewById(R.id.join);

        //비밀번호 보이는지에 대한 선언
        isShowedpw = false;


        //버튼에 대한 리스너 등록

        show_pw_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //비밀번호 보이기 버튼 누르기
                if(isShowedpw){
                    //만약 비밀번호가 보이는 상태라면?
                    show_pw_button.setImageResource(R.drawable.closedeye);
                    pw_textview.setInputType(android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    isShowedpw = false;
                }else{
                    //만약 비밀번호가 안 보이는 상태라면?
                    show_pw_button.setImageResource(R.drawable.openedeye);
                    pw_textview.setInputType(android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    isShowedpw = true;
                }
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                sc.replaceToJoin();
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
