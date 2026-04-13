package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText editId, editPw;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editId = findViewById(R.id.editId);
        editPw = findViewById(R.id.editPw);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String id = editId.getText().toString();
            String pw = editPw.getText().toString();

            // 간단한 테스트용 조건 (실제 앱에서는 DB 확인이 필요합니다)
            if (id.equals("admin") && pw.equals("1234")) {
                Toast.makeText(this, "교수님, 로그인 되었습니다.", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "아이디 또는 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}