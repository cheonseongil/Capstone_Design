package com.example.myapplication; // 본인의 패키지명 확인!

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProfessorSignupActivity extends AppCompatActivity {

    // 1. 사용할 뷰 객체 선언 (이름을 직관적으로 변경)
    EditText editId, editPw, editPwCheck;
    Button btnSignupFinal; // XML의 ID와 맞춤

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 2. 레이아웃 연결
        setContentView(R.layout.professor_signup);

        // 3. XML의 ID와 자바 객체 연결
        editId = findViewById(R.id.editId);
        editPw = findViewById(R.id.editPw);
        editPwCheck = findViewById(R.id.editPwCheck);

        // ★ 오류 해결 1: btnSignupFinal로 정확히 찾아주기
        btnSignupFinal = findViewById(R.id.btnSignupFinal);

        // 4. 가입완료 버튼 클릭 이벤트 설정
        btnSignupFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editId.getText().toString();
                String pw = editPw.getText().toString();
                String pwCheck = editPwCheck.getText().toString();

                // 간단한 유효성 검사
                if (id.isEmpty() || pw.isEmpty()) {
                    // ★ 오류 해결 2: ProfessorSignupActivity.this 로 모두 변경
                    Toast.makeText(ProfessorSignupActivity.this, "아이디와 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else if (!pw.equals(pwCheck)) {
                    Toast.makeText(ProfessorSignupActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    // 가입 성공 메시지
                    Toast.makeText(ProfessorSignupActivity.this, id + "님, 교수 회원가입 성공!", Toast.LENGTH_SHORT).show();

                    // (선택) 가입 완료 후 로그인 화면으로 돌아가게 하려면 아래 코드 추가
                    // finish();
                }
            }
        });
    }
}