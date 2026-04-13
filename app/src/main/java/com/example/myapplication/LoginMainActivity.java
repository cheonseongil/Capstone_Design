package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginMainActivity extends AppCompatActivity {

    private Button btnStudentLogin;
    private Button btnProfessorLogin;

    // 1. 기존 btnSignUp 대신 두 개의 버튼 변수 선언
    private Button btnStudentSignUp;
    private Button btnProfessorSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // XML 파일명이 다르면 수정하세요 (예: activity_login_main)

        btnStudentLogin = findViewById(R.id.btnStudentLogin);
        btnProfessorLogin = findViewById(R.id.btnProfessorLogin);

        // 2. 수정한 XML의 ID로 각각 연결
        btnStudentSignUp = findViewById(R.id.btnStudentSignUp);
        btnProfessorSignUp = findViewById(R.id.btnProfessorSignUp);

        // 학생 로그인
        btnStudentLogin.setOnClickListener(v -> {
            Intent intent = new Intent(LoginMainActivity.this, StudentLoginActivity.class);
            startActivity(intent);
        });

        // 교수 로그인
        btnProfessorLogin.setOnClickListener(v -> {
            Intent intent = new Intent(LoginMainActivity.this, ProfessorLoginActivity.class);
            startActivity(intent);
        });

        // 3. 학생 회원가입 버튼 클릭 이벤트
        btnStudentSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginMainActivity.this, StudentSignupActivity.class);
            startActivity(intent);
        });

        // 4. 교수 회원가입 버튼 클릭 이벤트
        // (이전에 만드셨던 SignupActivity가 교수용 회원가입 화면이므로 그쪽으로 연결했습니다)
        btnProfessorSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginMainActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }
}