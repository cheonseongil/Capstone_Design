package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Professor_SignupActivity extends AppCompatActivity {

    // 1. 사용할 뷰 객체 선언
    EditText editId, editPw, editPwCheck;
    Button btnSignup;
    View btnBack; // ★ 뒤로 가기 버튼 변수 추가 (View로 선언하면 ImageButton, ImageView 등 모두 호환됩니다)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 2. 레이아웃 연결
        setContentView(R.layout.professor_activity_signup);

        // 3. XML의 ID와 자바 객체 연결
        editId = findViewById(R.id.editId);
        editPw = findViewById(R.id.editPw);
        editPwCheck = findViewById(R.id.editPwCheck);
        btnSignup = findViewById(R.id.btnSignupFinal);

        // ★ 뒤로 가기 버튼 ID 연결 (XML에 id가 btnBack으로 되어 있어야 합니다)
        btnBack = findViewById(R.id.btnBack);

        // ==========================================
        // ★ 추가된 부분: 뒤로 가기 버튼 클릭 이벤트
        // ==========================================
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 현재 회원가입 화면을 종료하여 이전 화면(activity_main.xml)으로 부드럽게 돌아갑니다.
                finish();
            }
        });

        // 4. 가입확인 버튼 클릭 이벤트 설정
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editId.getText().toString();
                String pw = editPw.getText().toString();
                String pwCheck = editPwCheck.getText().toString();

                // 간단한 유효성 검사
                if (id.isEmpty() || pw.isEmpty()) {
                    Toast.makeText(Professor_SignupActivity.this, "아이디와 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else if (!pw.equals(pwCheck)) {
                    Toast.makeText(Professor_SignupActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    // 가입 성공 메시지
                    Toast.makeText(Professor_SignupActivity.this, id + "님, 교수 회원가입 성공!", Toast.LENGTH_SHORT).show();

                    // ★ 만약 가입 완료 후에도 자동으로 메인 화면으로 돌아가게 하고 싶다면 아래 주석을 해제하세요.
                    // finish();
                }
            }
        });
    }
}