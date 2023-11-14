package com.example.databaseprograming;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Main_recomend_hospital_recycleradapter extends RecyclerView.Adapter<Main_recomend_hospital_recycleradapter.ViewHolder>{


    private List<String> typeText = null;
    private List<String> nameText = null;
    private List<String> infoText = null;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1 ;
        TextView textView2 ;
        TextView textView3 ;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            textView1 = itemView.findViewById(R.id.recomend_hospital_type);
            textView2 = itemView.findViewById(R.id.recomend_hospital_name);
            textView3 = itemView.findViewById(R.id.recomend_hospital_info);
        }


    }

    Main_recomend_hospital_recycleradapter(ArrayList<String> tText, ArrayList<String> nText, ArrayList<String> iText) {
        typeText = tText;
        nameText = nText;
        infoText = iText;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public Main_recomend_hospital_recycleradapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.hospital_recomendation, parent, false) ;
        Main_recomend_hospital_recycleradapter.ViewHolder vh = new Main_recomend_hospital_recycleradapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(Main_recomend_hospital_recycleradapter.ViewHolder holder, int position) {
        String text1 = typeText.get(position);
        String text2 = nameText.get(position);
        String text3 = infoText.get(position);


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

        holder.textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return nameText.size() ;
    }
}
