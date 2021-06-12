package com.app.projectandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEtEmail;
    private EditText loginEtPassword;
    private Button loginBtnLog;
    private Button loginBtnRegister;
    private FirebaseAuth mAuth;
    String email, password;
    SharedPreferences sp;
    SharedPreferences.Editor speditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        mAuth = FirebaseAuth.getInstance();
        sp = getSharedPreferences("shared", MODE_PRIVATE);
        speditor = sp.edit();

        if (sp.getString("token", null) != null) {
            finish();
        }
        loginBtnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = loginEtEmail.getText().toString();
                password = loginEtPassword.getText().toString();
                if (!email.isEmpty() || !password.isEmpty()) {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                        String token = FirebaseAuth.getInstance().getUid();
                                        speditor.putString("token", token);
                                        speditor.apply();

                                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Authentication failed",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else
                    Toast.makeText(LoginActivity.this, "Empty", Toast.LENGTH_SHORT).show();
            }
        });

        loginBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        loginEtEmail = findViewById(R.id.login_et_email);
        loginEtPassword = findViewById(R.id.login_et_password);
        loginBtnLog = findViewById(R.id.login_btn_log);
        loginBtnRegister = findViewById(R.id.login_btn_register);
    }
}
