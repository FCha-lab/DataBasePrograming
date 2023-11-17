package com.example.databaseprograming;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Screen_controller extends AppCompatActivity {

    //Fragment 관련 매니져 변수 선언
    private Fragment previous_page;
    private Fragment current_page;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    //스크린 변수들 선언
    private Main_Screen main_screen;
    private Login_Screen login_screen;
    private Modification_Screen modification_screen;
    private Join_Screen join_screen;
    private Reservation_Screen reservation_screen;
    private Hospital_Info_Screen hospital_info_screen;

    //사용자 데이터 선언
    private boolean isLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_screen);

        fragmentManager = getSupportFragmentManager();

        //스크린 변수들 객체 생성 해주기
        main_screen = new Main_Screen();
        login_screen = new Login_Screen();
        modification_screen = new Modification_Screen();
        join_screen = new Join_Screen();
        reservation_screen = new Reservation_Screen();
        hospital_info_screen = new Hospital_Info_Screen();

        //사용자 데이터 불러오기
        isLogin = false;

        //초기 화면 설정하기
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.screen, main_screen);
        transaction.commit();

        previous_page = main_screen;
        current_page = main_screen;


    }


    //로그인 여부 반환 메서드
    public boolean getIsLogin() {
        return isLogin;
    }

    //화면 변환 메서드
    public void replaceFragment(Fragment target) {
        transaction.addToBackStack(null);
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.screen, target);
        transaction.commit();

        previous_page = current_page;
        current_page = target;
    }

    //요구하는 화면 클래스 반환
    public Fragment getScreen(Fragment target){
        if (target instanceof Main_Screen){
            return main_screen;
        }else if(target instanceof Login_Screen){
            return login_screen;
        }else if(target instanceof Modification_Screen){
            return modification_screen;
        }else if(target instanceof Join_Screen){
            return join_screen;
        }else if(target instanceof Reservation_Screen){
            return reservation_screen;
        }else if(target instanceof Hospital_Info_Screen){
            return hospital_info_screen;
        }
        return null;
    }

    @Override
    public void onBackPressed() {
        //현재 페이지 위치에 따라 뒤로가기 버튼의 이동.

        Log.d("페이지 상태", "\n현재 페이지 : " + current_page.getClass().getSimpleName() + "\n이전 페이지 : " + previous_page.getClass().getSimpleName());

        if (current_page instanceof Main_Screen) {
            //데이터 해제
            fragmentManager = null;
            transaction = null;
            previous_page = null;
            current_page = null;

            moveTaskToBack(true); // 태스크를 백그라운드로 이동
            finishAndRemoveTask(); // 액티비티 종료 + 태스크 리스트에서 지우기
            android.os.Process.killProcess(android.os.Process.myPid()); // 앱 프로세스 종료

        } else if (current_page instanceof Login_Screen || current_page instanceof Reservation_Screen) {

            replaceFragment(main_screen);

        } else if (previous_page instanceof  Login_Screen) {

            replaceFragment(login_screen);

        }
        return;
    }

}
