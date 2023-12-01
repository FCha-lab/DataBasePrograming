package com.example.databaseprograming;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.Collections;
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

public class Hospital_Search_Results_recyclerAdapter extends RecyclerView.Adapter<Hospital_Search_Results_recyclerAdapter.ViewHolder>{

    private List<String> nameText = null;
    private List<String> kindText = null;
    private List<Integer> likeCountList = null; // 좋아요 수를 저장하는 리스트
    private List<String> timeText = null;
    private List<String> pnumText = null;
    private List<String> addrText = null;

    // 각 아이템의 좋아요 상태를 저장하는 리스트
    private List<Boolean> isHospitalLikedList;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        ImageButton imageButton1;

        // 좋아요 상태를 저장하는 변수
        boolean isHospitalLiked;

        ViewHolder(View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조. (hold strong reference)
            textView1 = itemView.findViewById(R.id.hospital_name);
            textView2 = itemView.findViewById(R.id.hospital_kind);
            textView3 = itemView.findViewById(R.id.like_count);
            textView4 = itemView.findViewById(R.id.hospital_operation_time);
            textView5 = itemView.findViewById(R.id.hospital_phone_number);
            textView6 = itemView.findViewById(R.id.hospital_adress);
            imageButton1 = itemView.findViewById(R.id.hospital_like);
        }
    }

    Hospital_Search_Results_recyclerAdapter(ArrayList<String> aText, ArrayList<String> bText, ArrayList<Integer> likeCountList, ArrayList<String> dText, ArrayList<String> eText, ArrayList<String> fText) {
        nameText = aText;
        kindText = bText;
        this.likeCountList = likeCountList;
        timeText = dText;
        pnumText = eText;
        addrText = fText;

        // 각 아이템의 좋아요 상태를 초기화하는 코드 추가
        isHospitalLikedList = new ArrayList<>(Collections.nCopies(nameText.size(), false));
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public Hospital_Search_Results_recyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_hsr, parent, false);
        Hospital_Search_Results_recyclerAdapter.ViewHolder vh = new Hospital_Search_Results_recyclerAdapter.ViewHolder(view);
        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(Hospital_Search_Results_recyclerAdapter.ViewHolder holder, int position) {
        String text1 = nameText.get(position);
        String text2 = kindText.get(position);
        int likeCount = likeCountList.get(position);
        String text4 = timeText.get(position);
        String text5 = pnumText.get(position);
        String text6 = addrText.get(position);

        holder.textView1.setText(text1);
        holder.textView2.setText(text2);
        holder.textView3.setText(String.valueOf(likeCount));
        holder.textView4.setText(text4);
        holder.textView5.setText(text5);
        holder.textView6.setText(text6);

        // 각 아이템에 대한 좋아요 상태를 가져오기
        holder.isHospitalLiked = isHospitalLikedList.get(position);

        holder.textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 아이템을 클릭했을 때 수행할 작업 추가
            }
        });

        holder.textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 아이템을 클릭했을 때 수행할 작업 추가
            }
        });

        holder.imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();

                // 좋아요 상태를 먼저 반전시키고 UI 업데이트 수행
                boolean newLikedState = !holder.isHospitalLiked;

                // 좋아요 수 증가 또는 감소
                if (newLikedState) {
                    likeCountList.set(clickedPosition, likeCountList.get(clickedPosition) + 1);
                } else {
                    likeCountList.set(clickedPosition, likeCountList.get(clickedPosition) - 1);
                }

                // 좋아요 상태에 따라 UI 업데이트
                if (newLikedState) {
                    holder.imageButton1.setImageResource(R.drawable.heart_full);
                } else {
                    holder.imageButton1.setImageResource(R.drawable.heart_empty);
                }

                // 좋아요 수 업데이트
                holder.textView3.setText(String.valueOf(likeCountList.get(clickedPosition)));

                // 최종적으로 상태 업데이트
                holder.isHospitalLiked = newLikedState;
            }
        });
    }

    // getItemCount() - 전체 데이터 개수 리턴.
    @Override
    public int getItemCount() {
        return nameText.size();
    }
}

