package com.ics.admin;

import android.app.Activity;
import android.content.Context;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.MyViewHolder> {


    ArrayList Names;
    ArrayList<Integer> Images;
   FacultyFragment activity;

    public FacultyAdapter(FacultyFragment activity, ArrayList Names, ArrayList<Integer> Images)
    {
        this.activity = activity;
        this.Names = Names;
        this.Images = Images;

    }@NonNull
    @Override
    public FacultyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.faculty_adapter,viewGroup,false);

        return new FacultyAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FacultyAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(""+Names.get(i));
        myViewHolder.circleimageview.setImageResource(Images.get(i));

    }

    @Override
    public int getItemCount()
    {
        return Images.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView circleimageview;
        TextView name;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            circleimageview=(CircleImageView)itemView.findViewById(R.id.circleimageview);
        }
    }
}
