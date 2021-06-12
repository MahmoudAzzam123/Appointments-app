package com.app.projectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.projectandroid.data.CompanyData;
import com.app.projectandroid.data.ReservationData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

public class AddReservationActivity extends AppCompatActivity {

    private Spinner addSp;
    private EditText addEtDate;
    private EditText addEtTime;
    private Button addBtnAdd;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reservation);
        initView();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("MyData");

        data = new ArrayList<>();

        if (getIntent() != null) {
           ReservationData d = (ReservationData) getIntent().getSerializableExtra("data");
           addEtDate.setText(d.getDate());
           addEtTime.setText(d.getTime());
            for (int i = 0; i <data.size() ; i++) {
                if (d.getName().equals(data.get(i))){
                    addSp.setSelection(i);
                }
            }
        }
        myRef.child("company").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    CompanyData allData = d.getValue(CompanyData.class);

                    data.add(allData.getName());

                    ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, data);
                    addSp.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("Error", error.getMessage());
                Toast.makeText(getApplicationContext(), "Failed to read value", Toast.LENGTH_SHORT).show();
            }
        });


        addBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = addSp.getSelectedItem().toString();
                String date = addEtDate.getText().toString();
                String time = addEtTime.getText().toString();

                ReservationData rd = new ReservationData(name, date, time, FirebaseInstanceId.getInstance().getToken());
                myRef.child("Reservation").push().setValue(rd);
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    private void initView() {
        addSp = findViewById(R.id.add_sp);
        addEtDate = findViewById(R.id.add_et_date);
        addEtTime = findViewById(R.id.add_et_time);
        addBtnAdd = findViewById(R.id.add_btn_add);
    }
}
