package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginMainActivity extends AppCompatActivity {

    private Button btnStudentLogin;
    private Button btnProfessorLogin;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStudentLogin = findViewById(R.id.btnStudentLogin);
        btnProfessorLogin = findViewById(R.id.btnProfessorLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnStudentLogin.setOnClickListener(v -> {
            Intent intent = new Intent(LoginMainActivity.this, StudentLoginActivity.class);
            startActivity(intent);
        });

        /*btnProfessorLogin.setOnClickListener(v -> {
            Toast.makeText(LoginMainActivity.this, "교수 로그인 화면은 추후 구현", Toast.LENGTH_SHORT).show();
        });*/

        btnProfessorLogin.setOnClickListener(v -> {
            // 토스트 메시지 대신 ProfessorLoginActivity로 이동하는 Intent 작성
            Intent intent = new Intent(LoginMainActivity.this, ProfessorLoginActivity.class);
            startActivity(intent);
        });

        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginMainActivity.this, StudentSignupActivity.class);
            startActivity(intent);
        });
    }

}