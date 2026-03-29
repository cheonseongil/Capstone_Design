package com.example.myapplication; // 본인의 패키지명 확인

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.student_settings);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 뒤로 가기 버튼 작동
        ImageView btnBack = findViewById(R.id.btn_back_settings);
        btnBack.setOnClickListener(v -> finish());

        // SettingsActivity.java의 onCreate() 내부 하단에 추가
        androidx.constraintlayout.widget.ConstraintLayout btnPrivacy = findViewById(R.id.btn_privacy);

        btnPrivacy.setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(SettingsActivity.this, PersonalInfoActivity.class);
            startActivity(intent);
        });
    }
}