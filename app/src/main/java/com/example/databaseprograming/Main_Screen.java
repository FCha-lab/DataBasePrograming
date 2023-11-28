package com.example.databaseprograming;

import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main_Screen extends Fragment {


    //Screen Controller 변수 선언
    Screen_controller sc;

    //리사이클러뷰 - 진료과목별
    RecyclerView hospital_list;
    Main_hospital_type_recyclerAdapter hType_recycler;
    ArrayList<Integer> hospital_icon;
    ArrayList<String> hospital_text;

    //리사이클러뷰 - 추천
    RecyclerView recomend_list;
    Main_recommend_hospital_recyclerAdapter rList_recycler;
    ArrayList<String> recomend_type;
    ArrayList<String> recomend_name;
    ArrayList<String> recomend_info;

    //상단 아이콘
    ImageButton user_info_mani;

    //검색창
    EditText search_bar;
    ImageButton search_button;

    //3가지 아이콘
    ImageButton reserve_info;
    ImageButton diagno_info;
    ImageButton favorites;

    //서버 관련 변수 선언
    Modification_Check_RetrofitClient modification_check_retrofitClient;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.main_screen, container, false);

        //과목별 검색 recyclerview 리스트 추가
        hospital_icon = new ArrayList<>();
        hospital_icon.add(R.drawable.htype_ear);
        hospital_icon.add(R.drawable.htype_stomach);
        hospital_icon.add(R.drawable.htype_bone);
        hospital_icon.add(R.drawable.htype_eye);
        hospital_icon.add(R.drawable.htype_kid);
        hospital_icon.add(R.drawable.htype_pragnant);
        hospital_icon.add(R.drawable.htype_nerve);
        hospital_icon.add(R.drawable.htype_skin);
        hospital_icon.add(R.drawable.htype_mind);
        //hospital_icon.add(R.drawable.imageName);

        hospital_text = new ArrayList<>(
                Arrays.asList("이비인후과", "내과", "정형외과", "안과", "소아청소년과", "산부인과", "신경외과", "피부과", "정신건강의학과")
        );

        //추천 병원 recyclerview 리스트 추가
        recomend_type = new ArrayList<>(
                Arrays.asList("이비인후과", "이비인후과", "이비인후과")
        );
        recomend_name = new ArrayList<>(
                Arrays.asList("병원이에용", "한의원이에용", "모르겠어용")
        );
        recomend_info = new ArrayList<>(
                Arrays.asList("정보에양", "뭐일까용", "요구사항이 ㅈㄴ 많아영")
        );


        //리사이클러뷰 연결
        hospital_list = rootView.findViewById(R.id.hospital_type_recycle);
        recomend_list = rootView.findViewById(R.id.recomend_recycle);

        //리사이클러뷰 어댑터 초기화
        hospital_list.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        hType_recycler = new Main_hospital_type_recyclerAdapter(hospital_icon, hospital_text);
        hospital_list.setAdapter(hType_recycler);


        recomend_list.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rList_recycler = new Main_recommend_hospital_recyclerAdapter(recomend_type, recomend_name, recomend_info);
        recomend_list.setAdapter(rList_recycler);

        //리사이클러뷰 어댑터 가로 스크롤바 없애기
        hospital_list.setHorizontalScrollBarEnabled(false);
        recomend_list.setHorizontalScrollBarEnabled(false);


        //위젯을 연결
        user_info_mani = rootView.findViewById(R.id.user_info_mani);

        search_bar = rootView.findViewById(R.id.search_textview);
        search_button = rootView.findViewById(R.id.search_button);

        reserve_info = rootView.findViewById(R.id.reserve_info);
        diagno_info = rootView.findViewById(R.id.diagno_info);
        favorites = rootView.findViewById(R.id.favorites);

        //서버 관련 변수 초기화
        modification_check_retrofitClient = new Modification_Check_RetrofitClient();
        Modification_Check_RetrofitInterface r1 = modification_check_retrofitClient.getApiService(sc.getToken());


        //버튼에 대한 리스너 등록
        user_info_mani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //회원 정보 페이지 이동 시도
                //회원의 토큰이 null인지 확인
                if (sc.getToken() == null) {
                    //토큰이 없음, 로그인이 아예 안된 경우
                    sc.replaceFragment(new Login_Screen());
                }

                //만약 토큰이 있다면?
                r1.getUserInfoResponse().enqueue(new SetCallback<UserInfo_Response>(sc));

            }
        });

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        reserve_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //예약정보 이미지를 눌렀을 때
                sc.replaceFragment(new Reservation_Screen());

            }
        });

        diagno_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sc.replaceFragment(new Medical_Records_Inquiry_Screen());
            }
        });

        favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sc.replaceFragment(new Hospital_Search_Results_Screen());
            }
        });


        return rootView;
    }

    private class SetCallback<T> implements Callback<T> {

        Screen_controller sc;

        private SetCallback(Screen_controller sc){
            this.sc = sc;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            //정상적인 통신이 진행될 경우
            Log.d("통신 확인", response.toString());
            if (response.isSuccessful() || response.body() != null) {
                //정상적으로 토큰이 확인되었을 경우
                UserInfo_Response result = (UserInfo_Response) response.body();
                Log.d("통신 확인", result.toString());
                Log.d("통신 확인", "토큰 확인" + result.toString());


                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                //번들에 넘길 값 저장
                bundle.putString("userName", result.getUserName());
                bundle.putString("userId", result.getUserId());
                bundle.putString("phoneNumber", result.getPhoneNumber());
                bundle.putString("birthDate", result.getBirthDate());
                Fragment target = sc.getScreen(new Modification_Screen());//프래그먼트 선언
                target.setArguments(bundle);//번들을 프래그먼트로 보낼 준비

                sc.replaceFragment(new Modification_Screen());

            } else {
                //토큰에 문제가 생겼을 경우
                //오류 정보를 받아오기
                UserInfo_Response errorObject = null;
                ResponseBody rb = response.errorBody();
                if (rb != null) {
                    try {
                        // 오류 응답을 문자열로 읽어옴
                        String errorResponse = rb.string();

                        // Gson을 사용하여 JSON 문자열을 JsonObject로 파싱
                        Gson gson = new Gson();
                        errorObject = gson.fromJson(errorResponse, UserInfo_Response.class);


                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (rb != null) {
                            rb.close(); // 반드시 닫아주어야 함
                        }
                    }

                    if (errorObject != null) {
                        // 오류 응답 처리
                        Log.d("통신 확인", "오류 응답: " + errorObject.getStatus() + ", " + errorObject.getMessage());
                    }
                    sc.setToken(null);
                    sc.replaceFragment(new Login_Screen());
                }
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            //서버에 문제가 있을 경우
            Log.d("통신 확인", "onfailure 실패 ");
        }
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
