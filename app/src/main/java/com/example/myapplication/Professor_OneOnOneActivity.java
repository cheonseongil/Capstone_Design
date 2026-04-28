package com.example.myapplication; // 본인의 패키지명으로 확인 및 수정하세요

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Professor_OneOnOneActivity extends AppCompatActivity {

//    private ImageButton btnBack;
    private EditText editTitle, editContent;
    private LinearLayout btnConfirm, btnCancel;
    private TextView tvDeptSelect, tvRoomSelect;

    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 앞서 만든 1:1 문의 XML 레이아웃 연결
        setContentView(R.layout.professor_activity_one_on_one);

        // 1. 뷰(View) 아이디 연결
        btnBack = findViewById(R.id.btnBack);
        editTitle = findViewById(R.id.editTitle);
        editContent = findViewById(R.id.editContent);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnCancel = findViewById(R.id.btnCancel);
        tvDeptSelect = findViewById(R.id.tvDeptSelect);
        tvRoomSelect = findViewById(R.id.tvRoomSelect);

        // 뒤로가기 버튼
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 현재 화면을 종료하고 이전 화면으로 돌아갑니다.
            }
        });

        // 3. 취소 버튼 클릭 이벤트
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Professor_OneOnOneActivity.this, "문의 작성을 취소했습니다.", Toast.LENGTH_SHORT).show();
                finish(); // 화면 종료
            }
        });

        // 4. 확인(제출) 버튼 클릭 이벤트
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString().trim();
                String content = editContent.getText().toString().trim();

                // 제목이나 내용이 비어있는지 확인
                if (title.isEmpty()) {
                    Toast.makeText(Professor_OneOnOneActivity.this, "제목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return; // 더 이상 진행하지 않고 멈춤
                }

                if (content.isEmpty()) {
                    Toast.makeText(Professor_OneOnOneActivity.this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // (여기에 실제 서버나 데이터베이스로 문의 내용을 전송하는 코드가 들어갑니다)

                // 성공 메시지 띄우기
                Toast.makeText(Professor_OneOnOneActivity.this, "문의가 성공적으로 접수되었습니다.", Toast.LENGTH_SHORT).show();

                // 작성 완료 후 화면 닫기
                finish();
            }
        });

        // 5. (선택) 학과/호실 선택 텍스트 클릭 이벤트 임시 설정
        tvDeptSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Professor_OneOnOneActivity.this, "학과 선택 창이 열립니다.", Toast.LENGTH_SHORT).show();
            }
        });

        tvRoomSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Professor_OneOnOneActivity.this, "호실 선택 창이 열립니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}