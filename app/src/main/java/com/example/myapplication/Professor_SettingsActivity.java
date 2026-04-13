package com.example.myapplication; // 본인의 패키지명에 맞게 확인하세요.

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
// import android.widget.LinearLayout; // 만약 아래 버튼이 LinearLayout이라면 주석 해제

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Professor_SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // 레이아웃 파일 지정 (만약 activity_settings.xml을 쓴다면 R.layout.activity_settings로 변경)
        setContentView(R.layout.professor_settings);

        // 시스템 바(상태바, 내비게이션 바) 패딩 설정
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. 뒤로 가기 버튼
        ImageView btnBack = findViewById(R.id.btn_back_settings);
        btnBack.setOnClickListener(v -> finish()); // 현재 액티비티 종료

        // 2. 개인정보 관리 화면으로 이동 (추가 요청하신 코드 반영)
        // 주의: XML에 설정된 실제 레이아웃 타입(ConstraintLayout or LinearLayout)과 ID로 맞춰주세요.
        ConstraintLayout btnPrivacy = findViewById(R.id.btn_privacy);

        btnPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // PersonalInfoActivity로 이동하는 Intent 생성 및 시작
                Intent intent = new Intent(Professor_SettingsActivity.this, Professor_PersonalInfoActivity.class);
                startActivity(intent);
            }
        });

        // 3. 관리자 문의 화면으로 이동
        ConstraintLayout btnAdmin = findViewById(R.id.btn_admin);

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AdminContactActivity로 이동하는 Intent 생성 및 시작
                Intent intent = new Intent(Professor_SettingsActivity.this, Professor_AdminContactActivity.class);
                startActivity(intent);
            }
        });

        // Professor_SettingsActivity.java 의 onCreate() 내부 하단에 추가

        // 4. 데이터 관리 화면으로 이동
        ConstraintLayout btnData = findViewById(R.id.btn_data); // 제일 처음 만들어두셨던 데이터 관리 뷰의 ID

        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DataManagementActivity 로 이동
                Intent intent = new Intent(Professor_SettingsActivity.this, Professor_DataManagementActivity.class);
                startActivity(intent);
            }
        });

    }
}