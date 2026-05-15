package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView; // ★ TextView 사용을 위해 추가
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

// 날짜 동기화를 위해 추가
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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

        // =====================================================================
        // ★ 추가된 부분: 넘어온 "교시 숫자"를 받아서 동그라미 C를 1, 2, 3으로 바꿉니다.
        // =====================================================================
        TextView tvClassIcon = findViewById(R.id.icon_course);
        String passedClassNum = getIntent().getStringExtra("CLASS_NUM");

        // 전달받은 데이터가 있으면(null이 아니면) 'C' 대신 숫자로 변경
        if (passedClassNum != null && tvClassIcon != null) {
            tvClassIcon.setText(passedClassNum);
        }

        // 날짜 동기화 처리 (우측 상단 '예) 20xx/xx/xx' 부분)
        TextView tvDate = findViewById(R.id.tv_date);
        if(tvDate != null) {
            Calendar calendar = Calendar.getInstance();
            String dateSlash = new SimpleDateFormat("yyyy/MM/dd", Locale.KOREA).format(calendar.getTime());
            tvDate.setText("예) " + dateSlash);
        }
        // =====================================================================

        // --- 학생 클릭 이벤트 설정 ---

        LinearLayout student1 = findViewById(R.id.layout_student_1);
        if (student1 != null) {
            student1.setOnClickListener(v -> goToDetail("홍길동", "20xxxxxxxx"));
        }

        LinearLayout student2 = findViewById(R.id.layout_student_2);
        if (student2 != null) {
            student2.setOnClickListener(v -> goToDetail("김길동", "20xxxxxxxx"));
        }

        LinearLayout student3 = findViewById(R.id.layout_student_3);
        if (student3 != null) {
            student3.setOnClickListener(v -> goToDetail("이길동", "20xxxxxxxx"));
        }
    }

    private void goToDetail(String name, String id) {
        Intent intent = new Intent(Professor_StudentListActivity.this, Professor_StudentDetailActivity.class);
        intent.putExtra("STUDENT_NAME", name);
        intent.putExtra("STUDENT_ID", id);
        startActivity(intent);
    }
}