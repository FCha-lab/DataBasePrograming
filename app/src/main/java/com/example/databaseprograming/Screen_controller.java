package com.example.databaseprograming;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

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
    private Hospital_Search_Results_Screen hospital_search_results_screen;
    private Inquiry_of_Reservation_Information_Screen inquiry_of_reservation_information_screen;
    private Medical_Records_Inquiry_Screen medical_records_inquiry_screen;
    private Medical_Records_Screen medical_records_screen;

    //토큰 관련 암호화 변수 선언
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private MasterKey masterKey;


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
        hospital_search_results_screen = new Hospital_Search_Results_Screen();
        inquiry_of_reservation_information_screen = new Inquiry_of_Reservation_Information_Screen();
        medical_records_inquiry_screen = new Medical_Records_Inquiry_Screen();
        medical_records_screen = new Medical_Records_Screen();

        //토큰 관련 변수 셋팅
        try {
            masterKey = new MasterKey.Builder(this)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();
            sharedPreferences = EncryptedSharedPreferences.create(
                    this,
                    "sharedPreferences",
                    masterKey, // MasterKey 객체
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        editor = sharedPreferences.edit();

        //초기 화면 설정하기
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.screen, main_screen);
        transaction.commit();
        setToken(null);

        previous_page = main_screen;
        current_page = main_screen;


    }


    //토큰 반환 메서드
    public String getToken() {
        return sharedPreferences.getString("token", null);
    }

    public void setToken(String t) {
        editor.putString("token", t);
        editor.apply();
    }


    //화면 변환 메서드
    public void replaceFragment(Fragment t) {
        Fragment target = getScreen(t);

        transaction.addToBackStack(null);
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.screen, target);
        transaction.commit();

        previous_page = current_page;
        current_page = target;
    }

    //요구하는 화면 클래스 반환
    public Fragment getScreen(Fragment target) {
        if (target instanceof Main_Screen) {
            return main_screen;
        } else if (target instanceof Login_Screen) {
            return login_screen;
        } else if (target instanceof Modification_Screen) {
            return modification_screen;
        } else if (target instanceof Join_Screen) {
            return join_screen;
        } else if (target instanceof Reservation_Screen) {
            return reservation_screen;
        } else if (target instanceof Hospital_Search_Results_Screen) {
            return hospital_search_results_screen;
        } else if(target instanceof Hospital_Info_Screen) {
            return hospital_info_screen;
        }else if (target instanceof Inquiry_of_Reservation_Information_Screen) {
            return inquiry_of_reservation_information_screen;
        } else if (target instanceof Medical_Records_Screen) {
            return medical_records_screen;
        } else if (target instanceof Medical_Records_Inquiry_Screen) {
            return medical_records_inquiry_screen;
        }
        return null;
    }

    @Override
    public void onBackPressed() {
        //현재 페이지 위치에 따라 뒤로가기 버튼의 이동.
        if (true) {
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

            } else if (current_page instanceof Login_Screen || current_page instanceof Inquiry_of_Reservation_Information_Screen|| current_page instanceof Medical_Records_Inquiry_Screen) {

                replaceFragment(main_screen);

            } else if (current_page instanceof Medical_Records_Screen) {

                replaceFragment(medical_records_inquiry_screen);

            } else if (current_page instanceof Hospital_Info_Screen) {

                replaceFragment(hospital_search_results_screen);

            } else if (previous_page instanceof Main_Screen) {

                replaceFragment(main_screen);

            } else if (previous_page instanceof Login_Screen) {

                replaceFragment(login_screen);

            }
        } else {
            super.onBackPressed();

        }

        return;
    }

}
