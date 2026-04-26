package com.example.myapplication; // 본인의 패키지명 확인!

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton; // ImageButton 임포트 추가
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Student_Signup extends AppCompatActivity {

    // 1. 사용할 뷰 객체 선언
    EditText editId, editPw, editPwCheck;
    Button btnSignupFinal;
    ImageButton btnBack; // ★ 뒤로 가기 버튼 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 2. 레이아웃 연결
        setContentView(R.layout.student_signup);

        // 3. XML의 ID와 자바 객체 연결
        editId = findViewById(R.id.editId);
        editPw = findViewById(R.id.editPw);
        editPwCheck = findViewById(R.id.editPwCheck);
        btnSignupFinal = findViewById(R.id.btnSignupFinal);

        // ★ 뒤로 가기 버튼 찾기 연결 (XML의 id가 btnBack 맞습니다)
        btnBack = findViewById(R.id.btnBack);

        // ==========================================
        // ★ 핵심: 뒤로 가기 버튼 클릭 이벤트
        // ==========================================
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 현재 회원가입 화면을 종료하여 자연스럽게 이전 메인 화면으로 돌아갑니다.
                finish();
            }
        });

        // 4. 가입완료 버튼 클릭 이벤트 설정
        btnSignupFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editId.getText().toString();
                String pw = editPw.getText().toString();
                String pwCheck = editPwCheck.getText().toString();

                // 간단한 유효성 검사
                if (id.isEmpty() || pw.isEmpty()) {
                    Toast.makeText(Student_Signup.this, "아이디와 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else if (!pw.equals(pwCheck)) {
                    Toast.makeText(Student_Signup.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    // ★ 메시지를 학생에 맞게 수정했습니다.
                    Toast.makeText(Student_Signup.this, id + "님, 학생 회원가입 성공!", Toast.LENGTH_SHORT).show();

                    // 가입 완료 후 자동으로 로그인(메인) 화면으로 돌아가게 하려면 finish() 주석 해제
                    finish();
                }
            }
        });
    }
}