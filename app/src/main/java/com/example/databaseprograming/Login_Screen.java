package com.example.databaseprograming;

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

    //입력 관련 위젯 선언
    TextView id_textview;
    TextView pw_textview;
    ImageButton show_pw_button;

    //로그인 조작 관련 위젯 선언
    Button login_button;
    TextView find_id;
    TextView find_pw;
    TextView join;

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


        //버튼에 대한 리스너 등록

        show_pw_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        find_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        find_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return rootView;
    }
}
