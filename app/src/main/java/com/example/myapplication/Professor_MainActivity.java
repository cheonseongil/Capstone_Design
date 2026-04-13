package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Professor_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.professor_main); // 기존 레이아웃 유지

        // 시스템 바(상태바, 내비게이션 바) 패딩 설정
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // --- 추가된 토글 기능 로직 ---
        /* (생략된 주석 코드는 그대로 유지했습니다) */
        // ------------------------------

        // 1. [수정됨] 출석 관리 버튼 이벤트
        LinearLayout btnAttendanceRate = findViewById(R.id.btn_attendance_rate);
        btnAttendanceRate.setOnClickListener(v -> {
            // 출석 관리 화면(Professor_AttendanceManagementActivity)으로 이동하도록 변경했습니다.
            Intent intent = new Intent(Professor_MainActivity.this, Professor_AttendanceManagementActivity.class);
            startActivity(intent);
        });

        // 2. 설정(톱니바퀴) 아이콘 이벤트 (기존 코드 유지)
        ImageView imageViewSettings = findViewById(R.id.iv_settings);
        imageViewSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SettingsActivity로 이동하는 Intent 생성 및 시작
                Intent intent = new Intent(Professor_MainActivity.this, Professor_SettingsActivity.class);
                startActivity(intent);
            }
        });

        // 3. 일일출석 버튼(LinearLayout) 찾기
        LinearLayout btnDailyAttendance = findViewById(R.id.btn_daily_attendance);
        btnDailyAttendance.setOnClickListener(v -> {
            // 일일 출석 과목 목록 화면(Professor_AttendanceRateActivity)으로 이동
            Intent intent = new Intent(Professor_MainActivity.this, Professor_AttendanceRateActivity.class);
            startActivity(intent);
        });
    }
}