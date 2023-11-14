package com.example.databaseprograming;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Screen_controller extends AppCompatActivity {

    //Fragment 관련 매니져 변수 선언
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    //스크린 변수들 선언
    Main_Screen main_screen;
    Login_Screen login_screen;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_screen);

        fragmentManager = getSupportFragmentManager();

        //스크린 변수들 객체 생성 해주기
        main_screen = new Main_Screen(this);
        login_screen = new Login_Screen();


        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.screen, main_screen);
        transaction.commit();


    }

    public void replaceToLogin() {
        transaction.addToBackStack(null);
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.screen, login_screen);
        transaction.commit();
    }

}
