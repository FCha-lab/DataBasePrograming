package com.example.databaseprograming;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login_Screen extends AppCompatActivity {

    //입력 관련 위젯 선언
    TextView id_textview;
    TextView pw_textview;
    ImageButton show_pw_button;

    //로그인 조작 관련 위젯 선언
    Button login_button;
    TextView find_id;
    TextView find_pw;
    TextView join;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        //위젯을 연결
        id_textview = findViewById(R.id.id_textview);
        pw_textview = findViewById(R.id.pw_textview);
        show_pw_button = findViewById(R.id.show_pw_button);

        login_button = findViewById(R.id.login_button);
        find_id = findViewById(R.id.find_id);
        find_pw = findViewById(R.id.find_pw);
        join = findViewById(R.id.join);


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

    }
}
