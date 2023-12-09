package com.example.databaseprograming;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Medical_Records_Inquiry_recyclerAdapter extends RecyclerView.Adapter<Medical_Records_Inquiry_recyclerAdapter.ViewHolder>{

    private Screen_controller sc;

    private ArrayList<Medical_Records_Inquiry_Response> mri;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1 ;
        TextView textView2 ;
        TextView textView3 ;

        LinearLayout frame;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            textView1 = itemView.findViewById(R.id.medical_records_date);
            textView2 = itemView.findViewById(R.id.hospital_name);
            textView3 = itemView.findViewById(R.id.hospital_doctor_name);

            frame = itemView.findViewById(R.id.mri_button_1);
        }

    }

    Medical_Records_Inquiry_recyclerAdapter(ArrayList<Medical_Records_Inquiry_Response> mri, Screen_controller sc) {

        this.mri = mri;
        this.sc = sc;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public Medical_Records_Inquiry_recyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.item_mri, parent, false) ;
        Medical_Records_Inquiry_recyclerAdapter.ViewHolder vh = new Medical_Records_Inquiry_recyclerAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(Medical_Records_Inquiry_recyclerAdapter.ViewHolder holder, int position) {
        Medical_Records_Inquiry_Response target = mri.get(position);

        holder.textView1.setText(target.getRecordDate());
        holder.textView2.setText(target.getHospitalName());
        holder.textView3.setText(target.getDoctorName());

        holder.frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //개체를 눌렀을 때

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                //번들에 넘길 값 저장
                bundle.putInt("record_id", target.getRecordId());
                Fragment target = sc.getScreen(new Medical_Records_Screen());//프래그먼트 선언
                target.setArguments(bundle);//번들을 프래그먼트로 보낼 준비

                sc.replaceFragment(new Medical_Records_Screen(), true);
            }
        });

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mri.size() ;
    }
}
