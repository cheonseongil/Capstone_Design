package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Student_CourseGraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_activity_course_graph);

        // 뒤로가기
        ImageView btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        // 이전 화면에서 전달받은 데이터 꺼내기
        String courseName = getIntent().getStringExtra("COURSE_NAME");
        String courseIcon = getIntent().getStringExtra("COURSE_ICON");
        int presentRate = getIntent().getIntExtra("PRESENT_RATE", 0);
        int lateRate = getIntent().getIntExtra("LATE_RATE", 0);
        int absentRate = getIntent().getIntExtra("ABSENT_RATE", 0);

        // 텍스트뷰 데이터 설정
        ((TextView) findViewById(R.id.tv_title)).setText(courseName + " 출석률");
        ((TextView) findViewById(R.id.tv_summary_title)).setText(courseName);
        ((TextView) findViewById(R.id.tv_summary_icon)).setText(courseIcon);
        ((TextView) findViewById(R.id.tv_summary_detail)).setText(
                "출석 " + presentRate + "% / 지각 " + lateRate + "% / 결석 " + absentRate + "%"
        );

        // ★ 막대 그래프 동적 높이 조절
        setBarHeight(R.id.bar_present, R.id.space_present, presentRate);
        setBarHeight(R.id.bar_late, R.id.space_late, lateRate);
        setBarHeight(R.id.bar_absent, R.id.space_absent, absentRate);
    }

    // 막대와 빈공간의 비율(weight)을 조정하여 그래프 높이를 만드는 함수
    private void setBarHeight(int barId, int spaceId, int rate) {
        View bar = findViewById(barId);
        View space = findViewById(spaceId);

        // 막대 비율 적용
        LinearLayout.LayoutParams barParams = (LinearLayout.LayoutParams) bar.getLayoutParams();
        barParams.weight = rate;
        bar.setLayoutParams(barParams);

        // 위쪽 빈 공간 비율 적용 (100 - 내 비율)
        LinearLayout.LayoutParams spaceParams = (LinearLayout.LayoutParams) space.getLayoutParams();
        spaceParams.weight = 100 - rate;
        space.setLayoutParams(spaceParams);
    }
}