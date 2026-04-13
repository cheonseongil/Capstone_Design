package com.example.myapplication;

import android.content.Intent; // Intent 임포트 추가
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout; // ConstraintLayout 임포트 추가

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

        // 1. 뒤로 가기 버튼 이벤트
        ImageView btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        // ==========================================
        // 2. 네트워크 항목 클릭 시 상세 화면으로 이동 (추가된 부분)
        // ==========================================
        ConstraintLayout layoutNetwork = findViewById(R.id.layout_network);
        layoutNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 이전 단계에서 만든 NetworkAttendanceRateActivity로 이동
                Intent intent = new Intent(Student_AttendanceRateActivity.this, NetworkAttendanceRateActivity.class);
                startActivity(intent);
            }
        });
    }
}