package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StudentLoginActivity extends AppCompatActivity {

    private EditText etStudentId, etStudentPassword;
    private CheckBox cbKeepLogin;
    private Button btnFindId, btnFindPassword, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        etStudentId = findViewById(R.id.etStudentId);
        etStudentPassword = findViewById(R.id.etStudentPassword);
        cbKeepLogin = findViewById(R.id.cbKeepLogin);
        btnFindId = findViewById(R.id.btnFindId);
        btnFindPassword = findViewById(R.id.btnFindPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnFindId.setOnClickListener(v -> {
            Intent intent = new Intent(StudentLoginActivity.this, FindIdActivity.class);
            startActivity(intent);
        });

        btnFindPassword.setOnClickListener(v -> {
            Intent intent = new Intent(StudentLoginActivity.this, FindPasswordActivity.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(v -> {
            String id = etStudentId.getText().toString().trim();
            String password = etStudentPassword.getText().toString().trim();

            if (id.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "아이디와 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (cbKeepLogin.isChecked()) {
                Toast.makeText(this, "로그인 상태 유지 선택됨", Toast.LENGTH_SHORT).show();
            }

            Toast.makeText(this, "학생 로그인 성공(임시)", Toast.LENGTH_SHORT).show();

            // 학생 메인 뷰(MainActivity)로 이동하는 Intent
            Intent intent = new Intent(StudentLoginActivity.this, Student_MainActivity.class);
            startActivity(intent);

            // 로그인 화면은 뒤로 가기 버튼으로 다시 돌아오지 않도록 종료하는 것이 좋습니다.
            finish();
        });
    }
}