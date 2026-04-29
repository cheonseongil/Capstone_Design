package com.example.myapplication; // 본인의 패키지명에 맞게 확인하세요.

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Student_MainActivity extends AppCompatActivity {

    // 블루투스 권한 요청 고유 코드
    private static final int BLUETOOTH_PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.student_main);

        // 시스템 바 패딩 설정
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // --- 태그리스 블루투스 토글 기능 로직 ---
        TextView tvToggleOn = findViewById(R.id.tv_toggle_on);
        TextView tvToggleOff = findViewById(R.id.tv_toggle_off);

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // [ON] 버튼 클릭 이벤트
        tvToggleOn.setOnClickListener(v -> {
            if (bluetoothAdapter == null) {
                Toast.makeText(this, "블루투스를 지원하지 않는 기기입니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            // 블루투스 연결 권한 체크 (안드로이드 12 이상 필수)
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, BLUETOOTH_PERMISSION_REQUEST_CODE);
                return;
            }

            // 블루투스가 꺼져 있다면 켜기 요청 화면 띄우기
            if (!bluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivity(enableBtIntent);
            } else {
                Toast.makeText(this, "태그리스 출석을 활성화합니다.", Toast.LENGTH_SHORT).show();
            }

            // 시각적 ON 상태 디자인 적용
            tvToggleOn.setBackgroundColor(Color.parseColor("#E8E2FF"));
            tvToggleOn.setText("✓ ON");
            tvToggleOff.setBackgroundColor(Color.TRANSPARENT);
            tvToggleOff.setText("OFF");
        });

        // [OFF] 버튼 클릭 이벤트
        tvToggleOff.setOnClickListener(v -> {
            Toast.makeText(this, "태그리스 출석 기능을 종료합니다.", Toast.LENGTH_SHORT).show();

            // 시각적 OFF 상태 디자인 적용
            tvToggleOff.setBackgroundColor(Color.parseColor("#E8E2FF"));
            tvToggleOff.setText("✓ OFF");
            tvToggleOn.setBackgroundColor(Color.TRANSPARENT);
            tvToggleOn.setText("ON");
        });
        // ------------------------------

        // 1. 출석률 버튼 이벤트
        LinearLayout btnAttendanceRate = findViewById(R.id.btn_attendance_rate);
        btnAttendanceRate.setOnClickListener(v -> {
            Intent intent = new Intent(Student_MainActivity.this, Student_AttendanceRateActivity.class);
            startActivity(intent);
        });

        // 2. 일일 출석 버튼 이벤트
        LinearLayout btnDailyAttendance = findViewById(R.id.btn_daily_attendance);
        btnDailyAttendance.setOnClickListener(v -> {
            Intent intent = new Intent(Student_MainActivity.this, StudentDailyRateActivity.class);
            startActivity(intent);
        });

        // 3. 수강 과목 버튼 이벤트
        LinearLayout btnCourseTaken = findViewById(R.id.btn_course_taken);
        btnCourseTaken.setOnClickListener(v -> {
            Intent intent = new Intent(Student_MainActivity.this, TimetableActivity.class);
            startActivity(intent);
        });

        // 4. 설정(톱니바퀴) 아이콘 이벤트
        ImageView imageViewSettings = findViewById(R.id.iv_settings);
        imageViewSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Student_MainActivity.this, Student_SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}