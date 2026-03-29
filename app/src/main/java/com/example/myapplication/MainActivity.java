package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.student_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // XML에 추가했던 출석률 버튼 ID를 찾아 변수에 연결
        LinearLayout btnAttendanceRate = findViewById(R.id.btn_attendance_rate);

        // 버튼 클릭 이벤트 설정
        btnAttendanceRate.setOnClickListener(v -> {
            // AttendanceRateActivity로 이동하는 Intent 생성
            Intent intent = new Intent(MainActivity.this, AttendanceRateActivity.class);
            startActivity(intent); // 화면 전환 실행
        });

        ImageView btnSettings = findViewById(R.id.iv_settings);
        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

    }
}