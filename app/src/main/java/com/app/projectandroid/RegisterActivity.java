package com.app.projectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.app.projectandroid.data.UserData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class RegisterActivity extends AppCompatActivity {

    private EditText registerEtFname;
    private EditText registerEtLname;
    private EditText registerEtEmail;
    private EditText registerEtPassword;
    private EditText registerEtAddres;
    private EditText registerEtNumber;
    private EditText registerEtGender;
    private Button btn_register;
    private String fName, lName, email, password, address, gender, number;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("MyData");

        mAuth = FirebaseAuth.getInstance();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fName = registerEtFname.getText().toString();
                lName = registerEtLname.getText().toString();
                email = registerEtEmail.getText().toString();
                password = registerEtPassword.getText().toString();
                address = registerEtAddres.getText().toString();
                gender = registerEtGender.getText().toString();
                number = registerEtNumber.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                    UserData userData = new UserData(fName, lName, email, address, gender, number);
                                    myRef.child("users").child(FirebaseInstanceId.getInstance().getToken()).setValue(userData);
                                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(RegisterActivity.this, "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });
    }

    private void initView() {
        registerEtFname = findViewById(R.id.register_et_fname);
        registerEtLname = findViewById(R.id.register_et_lname);
        registerEtEmail = findViewById(R.id.register_et_email);
        registerEtPassword = findViewById(R.id.register_et_password);
        registerEtAddres = findViewById(R.id.register_et_addres);
        registerEtGender = findViewById(R.id.register_et_gender);
        registerEtNumber = findViewById(R.id.register_et_number);
        btn_register = findViewById(R.id.register_btn_register);
    }
}
