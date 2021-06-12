package com.app.projectandroid.fragment;

import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.projectandroid.R;
import com.app.projectandroid.data.CompanyData;
import com.app.projectandroid.recycler.CompanyRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.logging.Logger;


public class CompanyFragment1 extends Fragment {

    private RecyclerView rv;
    private ArrayList<CompanyData> data;
    private CompanyRecyclerAdapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    public CompanyFragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_company, container, false);
        rv = v.findViewById(R.id.company_rv);

        data = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("MyData").child("company");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    data.clear();
                    CompanyData allData = d.getValue(CompanyData.class);
                    data.add(allData);

                    adapter = new CompanyRecyclerAdapter(data);
                    adapter.notifyDataSetChanged();
                    rv.setAdapter(adapter);
                    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv.setHasFixedSize(true);

                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("Error", error.getMessage());
                Toast.makeText(getActivity(), "Failed to read value.", Toast.LENGTH_SHORT).show();
            }
        });


        return v;
    }
}
