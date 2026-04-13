package com.example.myapplication; // 본인의 패키지명에 맞게 확인하세요.

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

public class Student_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.student_main); // 기존 레이아웃 유지

        // 시스템 바(상태바, 내비게이션 바) 패딩 설정
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // --- 추가된 토글 기능 로직 ---
        // 태그리스 ON/OFF 텍스트뷰 찾기 (XML에 id를 부여하셨어야 합니다)
        TextView tvToggleOn = findViewById(R.id.tv_toggle_on);
        TextView tvToggleOff = findViewById(R.id.tv_toggle_off);

        // ON 버튼 클릭 이벤트
        tvToggleOn.setOnClickListener(v -> {
            // ON 상태 디자인 적용
            tvToggleOn.setBackgroundColor(Color.parseColor("#E8E2FF"));
            tvToggleOn.setText("✓ ON");

            // OFF 상태 디자인 해제 (투명 배경)
            tvToggleOff.setBackgroundColor(Color.TRANSPARENT);
            tvToggleOff.setText("OFF");

            // TODO: 실제 태그리스 기능 켜기 로직 추가
        });

        // OFF 버튼 클릭 이벤트
        tvToggleOff.setOnClickListener(v -> {
            // OFF 상태 디자인 적용
            tvToggleOff.setBackgroundColor(Color.parseColor("#E8E2FF"));
            tvToggleOff.setText("✓ OFF");

            // ON 상태 디자인 해제 (투명 배경)
            tvToggleOn.setBackgroundColor(Color.TRANSPARENT);
            tvToggleOn.setText("ON");

            // TODO: 실제 태그리스 기능 끄기 로직 추가
        });
        // ------------------------------

        // 1. 출석률 버튼 이벤트 (기존 코드)
        LinearLayout btnAttendanceRate = findViewById(R.id.btn_attendance_rate);
        btnAttendanceRate.setOnClickListener(v -> {
            Intent intent = new Intent(Student_MainActivity.this, Student_AttendanceRateActivity.class);
            startActivity(intent);
        });

        // ==========================================
        // 2. 일일 출석 버튼 이벤트 (추가된 부분)
        // ==========================================
        // XML에서 일일 출석 레이아웃의 ID가 btn_daily_attendance라고 가정합니다.
        LinearLayout btnDailyAttendance = findViewById(R.id.btn_daily_attendance);

        btnDailyAttendance.setOnClickListener(v -> {
            // 이전에 만든 StudentDailyRateActivity로 이동
            Intent intent = new Intent(Student_MainActivity.this, StudentDailyRateActivity.class);
            startActivity(intent);
        });

        // ==========================================
        // 3. 수강 과목 버튼 이벤트 (추가된 부분)
        // ==========================================
        LinearLayout btnCourseTaken = findViewById(R.id.btn_course_taken);
        btnCourseTaken.setOnClickListener(v -> {
            // 방금 같이 만든 시간표 화면(TimetableActivity)으로 이동
            Intent intent = new Intent(Student_MainActivity.this, TimetableActivity.class);
            startActivity(intent);
        });


        // 4. 설정(톱니바퀴) 아이콘 이벤트 (기존 코드 유지)
        ImageView imageViewSettings = findViewById(R.id.iv_settings);
        imageViewSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SettingsActivity로 이동하는 Intent 생성 및 시작
                Intent intent = new Intent(Student_MainActivity.this, Student_SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}