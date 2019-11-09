package com.ics.admin.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ics.admin.Fragment.F_StudyFragment;
import com.ics.admin.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class StudyMaterialAdapter extends RecyclerView.Adapter<StudyMaterialAdapter.MyViewHolder>{
    ArrayList Names;
    ArrayList<Integer> Images;
    F_StudyFragment activity;

    public StudyMaterialAdapter(F_StudyFragment activity, ArrayList Names, ArrayList<Integer> Images) {
        this.activity = activity;
        this.Names = Names;
        this.Images = Images;
    }


    @NonNull
    @Override
    public StudyMaterialAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.studymaterial, viewGroup, false);

        return new StudyMaterialAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StudyMaterialAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.study_material.setText("" + Names.get(i));
        myViewHolder.image_study.setImageResource(Images.get(i));
    }

    @Override
    public int getItemCount() {

        return Names.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public CircleImageView image_study;
        TextView study_material;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            study_material = (TextView) itemView.findViewById(R.id.study_material);
            image_study = (CircleImageView) itemView.findViewById(R.id.image_study);
        }
    }
}
