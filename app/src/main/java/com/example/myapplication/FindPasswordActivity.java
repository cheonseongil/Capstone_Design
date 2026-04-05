package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FindPasswordActivity extends AppCompatActivity {

    private EditText etResetId, etResetContact, etPasswordCode, etNewPassword;
    private Button btnPasswordSendCode, btnPasswordCodeCheck, btnPasswordResetDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);

        etResetId = findViewById(R.id.etResetId);
        etResetContact = findViewById(R.id.etResetContact);
        etPasswordCode = findViewById(R.id.etPasswordCode);
        etNewPassword = findViewById(R.id.etNewPassword);

        btnPasswordSendCode = findViewById(R.id.btnPasswordSendCode);
        btnPasswordCodeCheck = findViewById(R.id.btnPasswordCodeCheck);
        btnPasswordResetDone = findViewById(R.id.btnPasswordResetDone);

        btnPasswordSendCode.setOnClickListener(v ->
                Toast.makeText(this, "인증번호 발송(임시)", Toast.LENGTH_SHORT).show()
        );

        btnPasswordCodeCheck.setOnClickListener(v ->
                Toast.makeText(this, "인증번호 확인(임시)", Toast.LENGTH_SHORT).show()
        );

        btnPasswordResetDone.setOnClickListener(v -> {
            String id = etResetId.getText().toString().trim();
            String contact = etResetContact.getText().toString().trim();
            String code = etPasswordCode.getText().toString().trim();
            String newPassword = etNewPassword.getText().toString().trim();

            if (id.isEmpty() || contact.isEmpty() || code.isEmpty() || newPassword.isEmpty()) {
                Toast.makeText(this, "모든 항목을 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "비밀번호 재설정 완료(임시)", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}