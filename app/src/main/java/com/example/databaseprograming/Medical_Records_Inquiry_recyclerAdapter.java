package com.example.databaseprograming;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Medical_Records_Inquiry_recyclerAdapter extends RecyclerView.Adapter<Medical_Records_Inquiry_recyclerAdapter.ViewHolder>{


    private List<String> timeText = null;
    private List<String> hos_nameText = null;
    private List<String> doc_nameText = null;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1 ;
        TextView textView2 ;
        TextView textView3 ;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            textView1 = itemView.findViewById(R.id.medical_records_date);
            textView2 = itemView.findViewById(R.id.hospital_name);
            textView3 = itemView.findViewById(R.id.hospital_doctor_name);

        }

    }

    Medical_Records_Inquiry_recyclerAdapter(ArrayList<String> aText, ArrayList<String> bText, ArrayList<String> cText) {

        timeText = aText;
        hos_nameText = bText;
        doc_nameText = cText;

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
        String text1 = timeText.get(position);
        String text2 = hos_nameText.get(position);
        String text3 = doc_nameText.get(position);

        holder.textView1.setText(text1);
        holder.textView2.setText(text2);
        holder.textView3.setText(text3);


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

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return timeText.size() ;
    }
}
