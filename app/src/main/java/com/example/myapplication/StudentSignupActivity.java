package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentSignupActivity extends AppCompatActivity {

    private EditText etSignupId, etSignupPassword, etSignupPasswordCheck, etName;
    private EditText etStudentNumber, etDepartment, etBirth, etEmail, etPhone, etPhoneCode;
    private RadioGroup rgGender;
    private Button btnSendPhoneCode, btnSignupConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup);

        etSignupId = findViewById(R.id.etSignupId);
        etSignupPassword = findViewById(R.id.etSignupPassword);
        etSignupPasswordCheck = findViewById(R.id.etSignupPasswordCheck);
        etName = findViewById(R.id.etName);
        etStudentNumber = findViewById(R.id.etStudentNumber);
        etDepartment = findViewById(R.id.etDepartment);
        etBirth = findViewById(R.id.etBirth);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPhoneCode = findViewById(R.id.etPhoneCode);
        rgGender = findViewById(R.id.rgGender);
        btnSendPhoneCode = findViewById(R.id.btnSendPhoneCode);
        btnSignupConfirm = findViewById(R.id.btnSignupConfirm);

        btnSendPhoneCode.setOnClickListener(v ->
                Toast.makeText(this, "인증번호 발송(임시)", Toast.LENGTH_SHORT).show()
        );

        btnSignupConfirm.setOnClickListener(v -> {
            String id = etSignupId.getText().toString().trim();
            String pw = etSignupPassword.getText().toString().trim();
            String pwCheck = etSignupPasswordCheck.getText().toString().trim();
            String name = etName.getText().toString().trim();

            if (id.isEmpty() || pw.isEmpty() || pwCheck.isEmpty() || name.isEmpty()) {
                Toast.makeText(this, "필수 항목을 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!pw.equals(pwCheck)) {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            int checkedId = rgGender.getCheckedRadioButtonId();
            String gender = "";
            if (checkedId != -1) {
                RadioButton rb = findViewById(checkedId);
                gender = rb.getText().toString();
            }

            Toast.makeText(this, "회원가입 완료(임시)\n성별: " + gender, Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}