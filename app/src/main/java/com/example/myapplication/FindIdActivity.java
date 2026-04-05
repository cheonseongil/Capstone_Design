package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FindIdActivity extends AppCompatActivity {

    private EditText etFindIdPhone, etFindIdCode;
    private Button btnFindIdSendCode, btnFindIdCheck, btnFindId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id);

        etFindIdPhone = findViewById(R.id.etFindIdPhone);
        etFindIdCode = findViewById(R.id.etFindIdCode);
        btnFindIdSendCode = findViewById(R.id.btnFindIdSendCode);
        btnFindIdCheck = findViewById(R.id.btnFindIdCheck);
        btnFindId = findViewById(R.id.btnFindId);

        btnFindIdSendCode.setOnClickListener(v ->
                Toast.makeText(this, "인증번호 발송(임시)", Toast.LENGTH_SHORT).show()
        );

        btnFindIdCheck.setOnClickListener(v ->
                Toast.makeText(this, "인증번호 확인(임시)", Toast.LENGTH_SHORT).show()
        );

        btnFindId.setOnClickListener(v -> {
            String phone = etFindIdPhone.getText().toString().trim();
            String code = etFindIdCode.getText().toString().trim();

            if (phone.isEmpty() || code.isEmpty()) {
                Toast.makeText(this, "전화번호와 인증번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "아이디는 example123 입니다. (임시)", Toast.LENGTH_LONG).show();
        });
    }
}