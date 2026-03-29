package com.example.myapplication; // 본인의 패키지명에 맞게 확인하세요.

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PersonalInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // 레이아웃 파일 지정 (만약 activity_personal_info를 쓴다면 R.layout.activity_personal_info로 변경)
        setContentView(R.layout.student_personal_info);

        // 시스템 바(상태바, 내비게이션 바) 패딩 설정
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. 뒤로 가기 버튼
        ImageView btnBackInfo = findViewById(R.id.btn_back_info);
        btnBackInfo.setOnClickListener(v -> finish()); // 현재 액티비티 종료

        // 2. 비밀번호 변경 화면으로 이동
        // 주의: XML에 설정된 실제 TextView의 ID 이름과 동일하게 맞춰주세요.
        TextView buttonChangePassword = findViewById(R.id.buttonChangePassword);

        buttonChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ChangePasswordActivity로 이동하는 Intent 생성 및 시작
                Intent intent = new Intent(PersonalInfoActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}