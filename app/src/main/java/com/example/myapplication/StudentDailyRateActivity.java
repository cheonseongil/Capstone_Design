package com.example.myapplication; // 본인의 패키지명 확인

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StudentDailyRateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_daily_rate);

        // 1. 뒤로 가기 버튼 설정
        ImageView btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Student_MainActivity(학생 메인 화면)로 이동하는 Intent 생성
                Intent intent = new Intent(StudentDailyRateActivity.this, Student_MainActivity.class);

                // 새로운 화면을 띄우면서 기존에 쌓인 화면이 있다면 정리 (선택 사항)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                startActivity(intent);
                finish(); // 현재 '일일 출석' 화면은 종료
            }
        });

        // 2. 날짜 표시 설정 (기존 코드)
        TextView tvDate = findViewById(R.id.tv_date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd", Locale.KOREA);
        String currentDate = formatter.format(new Date());
        tvDate.setText(currentDate);
    }
}