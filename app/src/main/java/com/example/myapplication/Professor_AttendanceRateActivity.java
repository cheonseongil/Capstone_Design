package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Professor_AttendanceRateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.professor_attendance_rate);

        // 여백 설정 코드
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 뒤로가기 버튼 클릭 이벤트
        ImageView btnBack = findViewById(R.id.btn_back);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // --- 과목 클릭 시 일일 출석 UI로 화면 전환 ---

        // 1. 첫 번째 과목 클릭 이벤트
        ConstraintLayout clCourse1 = findViewById(R.id.cl_course_1);
        if (clCourse1 != null) {
            clCourse1.setOnClickListener(v -> {
                Intent intent = new Intent(Professor_AttendanceRateActivity.this, Professor_DailyAttendanceActivity.class);
                startActivity(intent);
            });
        }

        // 2. 두 번째 과목 클릭 이벤트 (추가됨)
        ConstraintLayout clCourse2 = findViewById(R.id.cl_course_2);
        if (clCourse2 != null) {
            clCourse2.setOnClickListener(v -> {
                Intent intent = new Intent(Professor_AttendanceRateActivity.this, Professor_DailyAttendanceActivity.class);
                startActivity(intent);
            });
        }

        // 3. 세 번째 과목 클릭 이벤트 (추가됨)
        ConstraintLayout clCourse3 = findViewById(R.id.cl_course_3);
        if (clCourse3 != null) {
            clCourse3.setOnClickListener(v -> {
                Intent intent = new Intent(Professor_AttendanceRateActivity.this, Professor_DailyAttendanceActivity.class);
                startActivity(intent);
            });
        }
    }
}