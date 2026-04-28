package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class Professor_MyInquiryActivity extends AppCompatActivity {

    // 뷰 변수 선언
    LinearLayout layoutMyQ1, layoutMyQ2;
    LinearLayout layoutMyA1, layoutMyA2;

    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor_activity_my_inquiry);

        // 뒤로가기 버튼
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 현재 화면을 종료하고 이전 화면으로 돌아갑니다.
            }
        });

        // 뷰 ID 연결
        layoutMyQ1 = findViewById(R.id.layoutMyQ1);
        layoutMyQ2 = findViewById(R.id.layoutMyQ2);

        layoutMyA1 = findViewById(R.id.layoutMyA1);
        layoutMyA2 = findViewById(R.id.layoutMyA2);

        // 첫 번째 문의 내역 클릭
        layoutMyQ1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleAnswer(1);
            }
        });

        // 두 번째 문의 내역 클릭
        layoutMyQ2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleAnswer(2);
            }
        });
    }

    // 선택한 답변은 열고, 나머지는 닫는 아코디언 로직
    private void toggleAnswer(int selectedIndex) {
        if (selectedIndex == 1) {
            // 1번이 열려있으면 닫고, 닫혀있으면 염 (그리고 2번은 닫음)
            if (layoutMyA1.getVisibility() == View.VISIBLE) {
                layoutMyA1.setVisibility(View.GONE);
            } else {
                layoutMyA1.setVisibility(View.VISIBLE);
                layoutMyA2.setVisibility(View.GONE);
            }
        } else if (selectedIndex == 2) {
            // 2번이 열려있으면 닫고, 닫혀있으면 염 (그리고 1번은 닫음)
            if (layoutMyA2.getVisibility() == View.VISIBLE) {
                layoutMyA2.setVisibility(View.GONE);
            } else {
                layoutMyA2.setVisibility(View.VISIBLE);
                layoutMyA1.setVisibility(View.GONE);
            }
        }
    }
}