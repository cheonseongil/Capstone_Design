package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Student_AttendanceRateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.student_attendance_rate);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. 뒤로 가기
        ImageView btnBack = findViewById(R.id.btn_back);
        if(btnBack != null) btnBack.setOnClickListener(v -> finish());

        // 2. 각 과목 클릭 이벤트 설정

        // (1) 모바일 프로그래밍 (XML에 ID 추가 필요: layout_course_1)
        ConstraintLayout layoutCourse1 = findViewById(R.id.layout_mobile);
        if (layoutCourse1 != null) {
            layoutCourse1.setOnClickListener(v -> goToGraph("모바일 프로그래밍", "M", 92, 8, 0));
        }

        // (2) 캡스톤 디자인 (XML에 ID 추가 필요: layout_course_2)
        ConstraintLayout layoutCourse2 = findViewById(R.id.layout_capston);
        if (layoutCourse2 != null) {
            layoutCourse2.setOnClickListener(v -> goToGraph("캡스톤 디자인", "C", 78, 0, 22));
        }

        // (3) 데이터 베이스 (XML에 ID 추가 필요: layout_course_3)
        ConstraintLayout layoutCourse3 = findViewById(R.id.layout_DB);
        if (layoutCourse3 != null) {
            layoutCourse3.setOnClickListener(v -> goToGraph("데이터 베이스", "DB", 85, 15, 0));
        }

        // (4) 네트워크
        ConstraintLayout layoutNetwork = findViewById(R.id.layout_network);
        if (layoutNetwork != null) {
            layoutNetwork.setOnClickListener(v -> goToGraph("네트워크", "N", 66, 10, 24));
        }
    }

    // 데이터를 담아서 그래프 화면으로 이동시키는 도우미 함수
    private void goToGraph(String name, String icon, int present, int late, int absent) {
        Intent intent = new Intent(Student_AttendanceRateActivity.this, Student_CourseGraphActivity.class);
        intent.putExtra("COURSE_NAME", name);
        intent.putExtra("COURSE_ICON", icon);
        intent.putExtra("PRESENT_RATE", present);
        intent.putExtra("LATE_RATE", late);
        intent.putExtra("ABSENT_RATE", absent);
        startActivity(intent);
    }
}