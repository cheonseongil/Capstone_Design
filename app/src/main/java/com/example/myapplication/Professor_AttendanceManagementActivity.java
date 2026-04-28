package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Professor_AttendanceManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // 새로 만드신 출석 관리 XML 파일을 연결합니다.
        setContentView(R.layout.professor_attendance_management);

        // 상단 상태바에 화면이 가려지는 것 방지 (XML 최상위 레이아웃에 android:id="@+id/main"이 있다고 가정)
        if (findViewById(R.id.main) != null) {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

// --- [새로 추가된 뒤로 가기 기능] ---
        // XML 파일에 있는 뒤로 가기 화살표 아이콘의 ID를 찾습니다.
        // (ID가 btn_back 또는 iv_back인지 본인의 XML 파일에서 확인하고 맞춰주세요!)
        ImageView btnBack = findViewById(R.id.btn_back);

        if (btnBack != null) {
            btnBack.setOnClickListener(v -> {
                finish(); // 현재 출석 관리 화면을 종료하고 이전 화면으로 돌아갑니다.
            });
        }

        // ==========================================
        // ★ 추가된 부분: 각 과목 클릭 시 학생 목록 화면으로 이동
        // ==========================================
        ConstraintLayout clCourse1 = findViewById(R.id.cl_course_1);
        if (clCourse1 != null) {
            clCourse1.setOnClickListener(v -> {
                Intent intent = new Intent(Professor_AttendanceManagementActivity.this, Professor_StudentListActivity.class);
                startActivity(intent);
            });
        }

        ConstraintLayout clCourse2 = findViewById(R.id.cl_course_2);
        if (clCourse2 != null) {
            clCourse2.setOnClickListener(v -> {
                Intent intent = new Intent(Professor_AttendanceManagementActivity.this, Professor_StudentListActivity.class);
                startActivity(intent);
            });
        }

        ConstraintLayout clCourse3 = findViewById(R.id.cl_course_3);
        if (clCourse3 != null) {
            clCourse3.setOnClickListener(v -> {
                Intent intent = new Intent(Professor_AttendanceManagementActivity.this, Professor_StudentListActivity.class);
                startActivity(intent);
            });
        }

    }
}