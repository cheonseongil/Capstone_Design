package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// 1. 클래스 이름이 ProfessorLoginActivity로 변경되어야 합니다.
public class ProfessorLoginActivity extends AppCompatActivity {

    // 변수 이름은 그대로 써도 작동하지만, 헷갈리지 않게 변경하는 것도 좋습니다.
    // (xml의 ID와 똑같이 맞춰야 합니다)
    private EditText etProfessorId, etProfessorPassword;
    private CheckBox cbKeepLogin;
    private Button btnFindId, btnFindPassword, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 2. 가장 중요! 보여줄 화면을 교수 로그인 XML(professor_login)로 변경해야 합니다.
        setContentView(R.layout.professor_login);

        // ⚠️ 주의: professor_login.xml 안에 있는 ID들과 일치해야 합니다.
        // 학생 코드를 그대로 복사했다면 xml 안의 ID가 아직 etStudentId 일 수 있습니다.
        // 가급적 xml에서도 ID를 etProfessorId 등으로 바꾸고 여기서도 똑같이 맞춰주세요.
        etProfessorId = findViewById(R.id.etProfessorId);
        etProfessorPassword = findViewById(R.id.etProfessorPassword);
        cbKeepLogin = findViewById(R.id.cbKeepLogin);
        btnFindId = findViewById(R.id.btnFindId);
        btnFindPassword = findViewById(R.id.btnFindPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnFindId.setOnClickListener(v -> {
            Intent intent = new Intent(ProfessorLoginActivity.this, FindIdActivity.class);
            startActivity(intent);
        });

        btnFindPassword.setOnClickListener(v -> {
            Intent intent = new Intent(ProfessorLoginActivity.this, FindPasswordActivity.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(v -> {
            String id = etProfessorId.getText().toString().trim();
            String password = etProfessorPassword.getText().toString().trim();

            if (id.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "아이디와 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (cbKeepLogin.isChecked()) {
                Toast.makeText(this, "로그인 상태 유지 선택됨", Toast.LENGTH_SHORT).show();
            }

            // 3. 토스트 메시지 변경
            Toast.makeText(this, "교수 로그인 성공(임시)", Toast.LENGTH_SHORT).show();

            // 4. 이동할 화면 변경! 교수님 전용 메인 뷰(Professor_MainActivity 등)로 가야 합니다.
            Intent intent = new Intent(ProfessorLoginActivity.this, Professor_MainActivity.class);
            startActivity(intent);

            finish();
        });
    }
}