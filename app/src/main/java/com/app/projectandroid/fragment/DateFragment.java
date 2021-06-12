package com.app.projectandroid.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import com.app.projectandroid.AddReservationActivity;
import com.app.projectandroid.R;
import com.app.projectandroid.adapter.ListGetAdapter;
import com.app.projectandroid.data.ReservationData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DateFragment extends Fragment {


    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ArrayList<ReservationData> array;
    private ListGetAdapter adapter;
    ArrayList<ReservationData> ddd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_date, container, false);

        CalendarView cv = v.findViewById(R.id.calendarView);
        final ListView list = v.findViewById(R.id.list);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("MyData");

        array = new ArrayList<>();

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                final String currentDate = i2 + "/" + (i1 + 1) + "/" + i;
                Toast.makeText(getActivity(), currentDate, Toast.LENGTH_SHORT).show();

                myRef.child("Reservation").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        array.clear();
                        for (DataSnapshot d : dataSnapshot.getChildren()) {
                            ReservationData allData = d.getValue(ReservationData.class);
                            array.add(allData);

                            for (int j = 0; j < array.size(); j++) {


                                if (array.get(j).getDate().equals(currentDate)) {
                                    ddd = new ArrayList<>();
                                    ddd.add(array.get(j));
                                    adapter = new ListGetAdapter(ddd, getActivity());
                                    list.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    break;
                                } else {
                                    array.clear();
                                    list.setAdapter(null);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Log.e("Error", error.getMessage());
                        Toast.makeText(getActivity(), "Failed to read value.", Toast.LENGTH_SHORT).show();
                    }
                });

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ReservationData reservationData = (ReservationData) adapter.getItem(position);
                        startActivity(new Intent(getActivity(), AddReservationActivity.class).
                                putExtra("data",reservationData));
                    }
                });

            }
        });


        return v;
    }
}
