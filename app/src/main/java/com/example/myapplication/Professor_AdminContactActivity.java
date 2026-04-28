package com.example.myapplication; // 본인의 실제 패키지명으로 수정하세요

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Professor_AdminContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor_activity_admin_contact);

        // 뒤로가기 버튼
        ImageView btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        // 관리자 통화 버튼
        findViewById(R.id.btn_call_admin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:01012345678"));
                startActivity(intent);
            }
        });

        // 나머지 버튼들도 동일한 방식으로 클릭 리스너를 설정할 수 있습니다.


        // ==========================================
        // 3. ★ 추가된 부분: 1:1 문의 버튼 클릭 이벤트
        // ==========================================
        findViewById(R.id.btn_one_on_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Professor_OneOnOneActivity 화면으로 이동
                Intent intent = new Intent(Professor_AdminContactActivity.this, Professor_OneOnOneActivity.class);
                startActivity(intent);
            }
        });

        // 나의 문의내역 버튼 클릭 이벤트
        findViewById(R.id.btn_my_inquiry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 나의 문의내역 화면으로 이동
                Intent intent = new Intent(Professor_AdminContactActivity.this, Professor_MyInquiryActivity.class);
                startActivity(intent);
            }
        });

        // 4. ★ 추가된 부분: 자주 묻는 질문 버튼 클릭 이벤트

        findViewById(R.id.btn_faq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 현재 화면에서 방금 만든 FaqActivity 화면으로 이동합니다.
                Intent intent = new Intent(Professor_AdminContactActivity.this, Professor_FaqActivity.class);
                startActivity(intent);
            }
        });
    }
}