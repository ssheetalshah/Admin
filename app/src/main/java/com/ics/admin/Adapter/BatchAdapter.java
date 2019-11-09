package com.ics.admin.Adapter;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ics.admin.Fragment.BatchFragment;
import com.ics.admin.R;
import com.ics.admin.AddStudentActivity;
import com.ics.admin.StudentActivity;

import java.util.ArrayList;

public class BatchAdapter extends RecyclerView.Adapter<BatchAdapter.MyViewHolder> {


    ArrayList Names;
   // ArrayList<Integer> Images;
    BatchFragment activity;

    public BatchAdapter(BatchFragment activity, ArrayList Names) {
        this.activity = activity;
        this.Names = Names;
      //  this.Images = Images;
    }

    @NonNull
    @Override
    public BatchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.batch_adapter, viewGroup, false);

        return new BatchAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BatchAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText("" + Names.get(i));
       // myViewHolder.circleimageview.setImageResource(Images.get(i));

    }

    @Override
    public int getItemCount() {

        return Names.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // public CircleImageView circleimageview;
        TextView name;
        ImageView add_student;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            add_student = (ImageView) itemView.findViewById(R.id.add_student);

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(activity.getActivity(), StudentActivity.class);
                    activity.startActivity(intent1);
                }
            });
add_student.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(activity.getActivity(),AddStudentActivity.class);
        activity.startActivity(intent);
    }
});


        }
    }
}