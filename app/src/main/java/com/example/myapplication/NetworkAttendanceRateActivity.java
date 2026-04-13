package com.example.myapplication; // 본인의 패키지명에 맞게 확인하세요.

import android.content.Intent; // Intent 임포트 추가
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class NetworkAttendanceRateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 위에서 만든 XML 레이아웃 파일 연결
        setContentView(R.layout.network_attendance_rate);

        // 뒤로 가기 버튼 이벤트 설정
        ImageView btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Student_AttendanceRateActivity(출석률 목록 화면)로 이동하는 Intent 생성
                Intent intent = new Intent(NetworkAttendanceRateActivity.this, Student_AttendanceRateActivity.class);

                // 2. 기존에 열려있던 출석률 화면이 있다면 그 위로 쌓인 화면들을 정리함
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                startActivity(intent);

                // 3. 현재의 상세 그래프 화면은 종료
                finish();
            }
        });
    }
}