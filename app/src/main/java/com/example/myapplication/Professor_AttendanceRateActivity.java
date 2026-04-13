package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle; // [cite: 786]
import android.widget.ImageView; // [cite: 787]
import androidx.activity.EdgeToEdge; // [cite: 788]
import androidx.appcompat.app.AppCompatActivity; // [cite: 789]
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets; // [cite: 790]
import androidx.core.view.ViewCompat; // [cite: 791]
import androidx.core.view.WindowInsetsCompat; // [cite: 792]

public class Professor_AttendanceRateActivity extends AppCompatActivity { // [cite: 793]

    @Override // [cite: 794]
    protected void onCreate(Bundle savedInstanceState) { // [cite: 795]
        super.onCreate(savedInstanceState); // [cite: 796]
        EdgeToEdge.enable(this); // [cite: 797]
        setContentView(R.layout.professor_attendance_rate); // [cite: 798]

        // 깨져있던 여백 설정 코드 정상화 [cite: 798, 799, 800, 801, 802, 803, 806, 807, 808, 809, 810]
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 뒤로가기 버튼 클릭 이벤트 (오류 수정됨) [cite: 811, 812, 813]
        ImageView btnBack = findViewById(R.id.btn_back);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // --- 과목 클릭 시 일일 출석 UI로 화면 전환 (Intent 추가) ---
        ConstraintLayout clCourse1 = findViewById(R.id.cl_course_1);

        if (clCourse1 != null) {
            clCourse1.setOnClickListener(v -> {
                // Professor_DailyAttendanceActivity 로 이동
                Intent intent = new Intent(Professor_AttendanceRateActivity.this, Professor_DailyAttendanceActivity.class);
                startActivity(intent);
            });
        }
    }
}