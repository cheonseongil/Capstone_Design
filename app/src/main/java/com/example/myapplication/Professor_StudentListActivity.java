package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Professor_StudentListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.professor_activity_student_list);

        // 뒤로 가기
        ImageView btnBack = findViewById(R.id.btn_back);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // --- 학생 클릭 이벤트 설정 ---

        // 1. 홍길동 클릭
        LinearLayout student1 = findViewById(R.id.layout_student_1);
        if (student1 != null) {
            student1.setOnClickListener(v -> goToDetail("홍길동", "20xxxxxxxx"));
        }

        // 2. 김길동 클릭
        LinearLayout student2 = findViewById(R.id.layout_student_2);
        if (student2 != null) {
            student2.setOnClickListener(v -> goToDetail("김길동", "20xxxxxxxx"));
        }

        // 3. 이길동 클릭
        LinearLayout student3 = findViewById(R.id.layout_student_3);
        if (student3 != null) {
            student3.setOnClickListener(v -> goToDetail("이길동", "20xxxxxxxx"));
        }
    }

    // 데이터를 담아 상세 화면으로 이동시키는 함수
    private void goToDetail(String name, String id) {
        Intent intent = new Intent(Professor_StudentListActivity.this, Professor_StudentDetailActivity.class);
        intent.putExtra("STUDENT_NAME", name); // 선택한 이름 담기
        intent.putExtra("STUDENT_ID", id);     // 선택한 학번 담기
        startActivity(intent);
    }
}