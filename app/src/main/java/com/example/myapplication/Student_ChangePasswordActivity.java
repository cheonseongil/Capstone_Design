package com.example.myapplication; // 본인의 패키지명으로 변경하세요.

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Student_ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.student_change_password);

        // 하단 토글 버튼 뷰 찾기
        LinearLayout layoutConfirm = findViewById(R.id.layoutConfirm);
        LinearLayout layoutCancel = findViewById(R.id.layoutCancel);

        // 취소 버튼 클릭 리스너 설정
        layoutCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 현재 화면을 종료하고 이전 화면(개인정보 관리 뷰)으로 돌아갑니다.
                finish();
            }
        });

        // 확인 버튼 클릭 리스너 설정 (실제 비밀번호 변경 로직 구현 위치)
        layoutConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 여기에 비밀번호 변경 로직을 구현합니다.
                // 예: 입력 필드 확인, 서버 API 호출 등
                Toast.makeText(Student_ChangePasswordActivity.this, "비밀번호 변경이 시도됩니다.", Toast.LENGTH_SHORT).show();
            }
        });

        // 비밀번호 눈 모양 아이콘 클릭 시 비밀번호 보이기/숨기기 로직은 필요에 따라 추가하세요.
    }
}