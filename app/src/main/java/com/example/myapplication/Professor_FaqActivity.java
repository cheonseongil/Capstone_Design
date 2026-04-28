package com.example.myapplication; // 본인 패키지명으로 확인

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class Professor_FaqActivity extends AppCompatActivity {

    // 질문(Q) 레이아웃과 답변(A) 레이아웃 변수 선언
    LinearLayout layoutQ1, layoutQ2;
    LinearLayout layoutA1, layoutA2;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor_activity_faq);

        // 뒤로가기 버튼
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 현재 화면을 종료하고 이전 화면으로 돌아갑니다.
            }
        });

        // 뷰 연결
        layoutQ1 = findViewById(R.id.layoutQ1);
        layoutQ2 = findViewById(R.id.layoutQ2);

        layoutA1 = findViewById(R.id.layoutA1);
        layoutA2 = findViewById(R.id.layoutA2);

        // 첫 번째 질문 클릭 이벤트
        layoutQ1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleAnswer(1); // 1번을 선택했다고 알려줌
            }
        });

        // 두 번째 질문 클릭 이벤트
        layoutQ2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleAnswer(2); // 2번을 선택했다고 알려줌
            }
        });
    }

    // ★ 핵심 로직: 선택한 답변은 열고, 나머지는 닫는 기능
    private void toggleAnswer(int selectedIndex) {
        // 1번을 눌렀을 때
        if (selectedIndex == 1) {
            // 이미 1번이 열려있다면 닫기, 아니면 열기
            if (layoutA1.getVisibility() == View.VISIBLE) {
                layoutA1.setVisibility(View.GONE);
            } else {
                layoutA1.setVisibility(View.VISIBLE);
                layoutA2.setVisibility(View.GONE); // 2번은 강제로 닫음
            }
        }
        // 2번을 눌렀을 때
        else if (selectedIndex == 2) {
            // 이미 2번이 열려있다면 닫기, 아니면 열기
            if (layoutA2.getVisibility() == View.VISIBLE) {
                layoutA2.setVisibility(View.GONE);
            } else {
                layoutA2.setVisibility(View.VISIBLE);
                layoutA1.setVisibility(View.GONE); // 1번은 강제로 닫음
            }
        }
    }
}