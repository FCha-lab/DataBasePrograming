package com.example.databaseprograming;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class hospital_recyle_adapter extends RecyclerView.Adapter<hospital_recyle_adapter.ViewHolder> {

    private ArrayList<String> hospital_name_list;
    private ArrayList<Integer> hospital_icon_list;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageButton hospital_icon;
        public TextView hospital_name;

        public ViewHolder(@NonNull View item){
            super(item);
            hospital_icon = item.findViewById(R.id.hospital_icon + );
            hospital_icon = item.findViewById(R.id.hospital_icon + );
        }
    }

    public hospital_recyle_adapter (ArrayList<String> nlist, ArrayList<Integer> ilist){
        ho
    }
}
