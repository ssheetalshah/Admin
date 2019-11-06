package com.ics.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;


public class FacultyFragment extends Fragment {

RecyclerView recyclerView;
com.github.clans.fab.FloatingActionButton fab;
    ArrayList Names = new ArrayList<>(Arrays.asList("Faculty Name", " Faculty Number", "Faculty Class", "Address"));
    ArrayList<Integer> Images = new ArrayList<>(Arrays.asList(R.drawable.girl,R.drawable.ic_community));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_faculty, container, false);

       // FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
       FloatingActionButton fab=(FloatingActionButton)view.findViewById(R.id.fab);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(getActivity(),AddFacultyActivity.class);
              startActivity(intent);

          }
      });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView

       FacultyAdapter facultyAdapter = new FacultyAdapter(FacultyFragment.this,Names,Images);
        recyclerView.setAdapter(facultyAdapter); // set the Adapter to RecyclerView





        return view;


    }




}

