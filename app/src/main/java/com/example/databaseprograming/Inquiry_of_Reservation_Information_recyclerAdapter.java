package com.example.databaseprograming;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.google.gson.Gson;

import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Inquiry_of_Reservation_Information_recyclerAdapter extends RecyclerView.Adapter<Inquiry_of_Reservation_Information_recyclerAdapter.ViewHolder> {


    private Screen_controller sc;

    private ArrayList<Inquiry_of_Reservation_Information_Response> ior;

    private Context context;

    //서버 관련 변수 선언
    private Reservation_RetrofitClient inquiry_of_reservation_information_retrofit_client;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        ImageButton imageButton1;

        ViewHolder(View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조. (hold strong reference)
            textView1 = itemView.findViewById(R.id.status);
            textView2 = itemView.findViewById(R.id.hospital_name);
            textView3 = itemView.findViewById(R.id.hospital_address);
            textView4 = itemView.findViewById(R.id.hospital_ior_date);
            textView5 = itemView.findViewById(R.id.hospital_ior_time);
            imageButton1 = itemView.findViewById(R.id.x_red);

        }

    }

    Inquiry_of_Reservation_Information_recyclerAdapter(ArrayList<Inquiry_of_Reservation_Information_Response> ior, Context context, Screen_controller sc) {
        this.ior = ior;
        this.context = context;
        this.sc = sc;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public Inquiry_of_Reservation_Information_recyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_ior, parent, false);
        Inquiry_of_Reservation_Information_recyclerAdapter.ViewHolder vh = new Inquiry_of_Reservation_Information_recyclerAdapter.ViewHolder(view);

        //서버 관련 변수 선언
        inquiry_of_reservation_information_retrofit_client = new Reservation_RetrofitClient();

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(Inquiry_of_Reservation_Information_recyclerAdapter.ViewHolder holder, int position) {
        Inquiry_of_Reservation_Information_Response target = ior.get(position);

        holder.textView1.setText(target.getAppointmentStatus());
        holder.textView2.setText(target.getHospitalName());
        holder.textView3.setText(target.getHospitalAddress());
        holder.textView4.setText(target.getDate());
        holder.textView5.setText(target.getTime());

        final int adapterPosition = holder.getAdapterPosition();  // final로 선언

        holder.imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // AlertDialog.Builder를 사용하여 팝업 창을 생성합니다.
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                // 팝업 창의 제목과 메시지를 설정합니다.
                builder.setTitle("예약 취소");
                builder.setMessage("정말로 취소하시겠습니까?");

                // '예' 버튼을 눌렀을 때의 동작을 정의합니다.
                dialogListener dl = new dialogListener(holder, target, holder.getAdapterPosition());
                builder.setPositiveButton("예", dl);

                // '아니오' 버튼을 눌렀을 때의 동작을 정의합니다.
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 아니오 버튼을 눌렀을 때 수행할 동작을 여기에 추가
                        dialogInterface.dismiss(); // 팝업 창을 닫습니다.
                    }
                });

                // 팝업 창을 생성하고 표시합니다.
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private class dialogListener implements DialogInterface.OnClickListener{

        private Inquiry_of_Reservation_Information_recyclerAdapter.ViewHolder holder;
        private Inquiry_of_Reservation_Information_Response target;
        private int position;

        private dialogListener(Inquiry_of_Reservation_Information_recyclerAdapter.ViewHolder holder, Inquiry_of_Reservation_Information_Response target, int position) {
            this.holder = holder;
            this.target = target;
            this.position = position;
        }

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            // 예 버튼을 눌렀을 때 수행할 동작을 여기에 추가
            //서버 관련 처리
            Reservation_RetrofitInterface r1 = inquiry_of_reservation_information_retrofit_client.getApiService(sc.getToken());

            r1.deleteReservation(target.getId()).enqueue(new Callback<Reservation_Information_Delete_Response>() {
                @Override
                public void onResponse(Call<Reservation_Information_Delete_Response> call, Response<Reservation_Information_Delete_Response> response) {
                    if (response.isSuccessful() || response.body() != null) {
                        Toast.makeText(context, "예약이 취소되었습니다.", Toast.LENGTH_SHORT).show();

                        Log.d("리사이클러 삭제 테스트",response.body().toString());
                        Log.d("리사이클러 삭제 테스트",String.valueOf(response.code()));

                        // 리사이클러뷰에서 아이템 삭제
                        int adapterPosition = holder.getAdapterPosition();
                        if (adapterPosition != RecyclerView.NO_POSITION) {
                            removeItem(adapterPosition);
                        }
                    }else{
                        // 401 오류 처리
                        Reservation_Information_Delete_Response errorObject = null;
                        ResponseBody rb = response.errorBody();
                        if (rb != null) {
                            try {
                                // 오류 응답을 문자열로 읽어옴
                                String errorResponse = rb.string();

                                // Gson을 사용하여 JSON 문자열을 JsonObject로 파싱
                                Gson gson = new Gson();
                                errorObject = gson.fromJson(errorResponse, Reservation_Information_Delete_Response.class);

                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                if (rb != null) {
                                    rb.close(); // 반드시 닫아주어야 함
                                }
                            }

                            if (target.getAppointmentStatus().equals("진료완료")) {
                                // 오류 응답 처리
                                Toast.makeText(context, "진료완료된 예약은 취소가 불가합니다.", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<Reservation_Information_Delete_Response> call, Throwable t) {

                }
            });

        }
    }


    // 아이템 삭제 메서드
    private void removeItem(int position) {
        if (position >= 0 && position < ior.size()) {
            ior.remove(position);
            notifyItemRemoved(position);
            Log.d("position, size = ",String.valueOf(position) + ", " + String.valueOf(ior.size()));
        }
    }
    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return ior.size() ;
    }
}