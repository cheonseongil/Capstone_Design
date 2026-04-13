package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    // 1. 사용할 뷰 객체 선언
    EditText editId, editPw, editPwCheck;
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 2. 레이아웃 연결 (XML 파일 이름과 반드시 똑같아야 함!)
        // 파일명이 activity_signup.xml 이라면 아래처럼 수정하세요.
        setContentView(R.layout.activity_signup);

        // 3. XML의 ID와 자바 객체 연결
        editId = findViewById(R.id.editId);
        editPw = findViewById(R.id.editPw);
        editPwCheck = findViewById(R.id.editPwCheck);
        btnSignup = findViewById(R.id.btnSignupFinal);

        // 4. 가입확인 버튼 클릭 이벤트 설정
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editId.getText().toString();
                String pw = editPw.getText().toString();
                String pwCheck = editPwCheck.getText().toString();

                // 간단한 유효성 검사
                if (id.isEmpty() || pw.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "아이디와 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else if (!pw.equals(pwCheck)) {
                    Toast.makeText(SignupActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    // 가입 성공 메시지
                    Toast.makeText(SignupActivity.this, id + "님, 교수 회원가입 성공!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}