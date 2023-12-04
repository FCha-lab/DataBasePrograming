package com.example.databaseprograming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class Inquiry_of_Reservation_Information_recyclerAdapter extends RecyclerView.Adapter<Inquiry_of_Reservation_Information_recyclerAdapter.ViewHolder>{


    private ArrayList<Inquiry_of_Reservation_Informaion_Response> ior;

    private Context context;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1 ;
        TextView textView2 ;
        TextView textView3 ;
        TextView textView4 ;
        TextView textView5 ;
        ImageButton imageButton1 ;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            textView1 = itemView.findViewById(R.id.status);
            textView2 = itemView.findViewById(R.id.hospital_name);
            textView3 = itemView.findViewById(R.id.hospital_address);
            textView4 = itemView.findViewById(R.id.hospital_ior_date);
            textView5 = itemView.findViewById(R.id.hospital_ior_time);
            imageButton1 = itemView.findViewById(R.id.x_red);

        }

    }

    Inquiry_of_Reservation_Information_recyclerAdapter(ArrayList<Inquiry_of_Reservation_Informaion_Response> ior, Context context) {

        this.ior = ior;
        this.context = context;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public Inquiry_of_Reservation_Information_recyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.item_ior, parent, false) ;
        Inquiry_of_Reservation_Information_recyclerAdapter.ViewHolder vh = new Inquiry_of_Reservation_Information_recyclerAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(Inquiry_of_Reservation_Information_recyclerAdapter.ViewHolder holder, int position) {
        Inquiry_of_Reservation_Informaion_Response target = ior.get(position);

        holder.textView1.setText(target.getStatus());
        holder.textView2.setText(target.getHospitalName());
        holder.textView3.setText(target.getHospitalAddress());
        holder.textView4.setText(target.getDate());
        holder.textView5.setText(target.getTime());

        holder.textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // AlertDialog.Builder를 사용하여 팝업 창을 생성합니다.
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                // 팝업 창의 제목과 메시지를 설정합니다.
                builder.setTitle("예약 취소");
                builder.setMessage("정말로 취소하시겠습니까?");

                // '예' 버튼을 눌렀을 때의 동작을 정의합니다.
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 예 버튼을 눌렀을 때 수행할 동작을 여기에 추가
                        Toast.makeText(context, "예약이 취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

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

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return ior.size() ;
    }
}
