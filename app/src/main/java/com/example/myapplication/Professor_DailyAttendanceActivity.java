package com.example.myapplication; // 본인 패키지명에 맞게 수정

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Professor_DailyAttendanceActivity extends AppCompatActivity {

    private TextView tvStatus1, tvStatus2, tvStatus3;
    private TextView tvCountPresent, tvCountLate, tvCountYugo, tvCountAbsent;
    private ImageView ivStatusIcon1, ivStatusIcon2, ivStatusIcon3;

    private static final int BLUETOOTH_PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.professor_daily_attendance);

        if (findViewById(R.id.main) != null) {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        ImageView btnBack = findViewById(R.id.iv_back);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // =====================================================================
        // ★ 추가된 부분: 이전 화면에서 보낸 "교시 숫자"를 받아서 동그라미 아이콘을 변경합니다.
        // =====================================================================
        TextView tvClassIcon = findViewById(R.id.tv_class_icon);
        String passedClassNum = getIntent().getStringExtra("CLASS_NUM");

        // 전달받은 데이터가 있으면(null이 아니면) 글자를 바꿉니다.
        if (passedClassNum != null) {
            tvClassIcon.setText(passedClassNum);
        }
        // =====================================================================

        tvStatus1 = findViewById(R.id.tv_status_1);
        tvStatus2 = findViewById(R.id.tv_status_2);
        tvStatus3 = findViewById(R.id.tv_status_3);

        tvCountPresent = findViewById(R.id.tv_count_present);
        tvCountLate = findViewById(R.id.tv_count_late);
        tvCountYugo = findViewById(R.id.tv_count_yugo);
        tvCountAbsent = findViewById(R.id.tv_count_absent);

        ivStatusIcon1 = findViewById(R.id.iv_status_icon_1);
        ivStatusIcon2 = findViewById(R.id.iv_status_icon_2);
        ivStatusIcon3 = findViewById(R.id.iv_status_icon_3);

        TextView btnClassStart = findViewById(R.id.btn_class_start);
        TextView btnClassEnd = findViewById(R.id.btn_class_end);

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        btnClassStart.setOnClickListener(v -> {
            if (bluetoothAdapter == null) {
                Toast.makeText(this, "이 기기는 블루투스를 지원하지 않습니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, BLUETOOTH_PERMISSION_REQUEST_CODE);
                return;
            }

            if (!bluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivity(enableBtIntent);
            }

            btnClassStart.setText("✓ 강의 시작");
            btnClassStart.setBackgroundResource(R.drawable.bg_pill_left_filled);
            btnClassEnd.setText("강의 종료");
            btnClassEnd.setBackground(null);
        });

        btnClassEnd.setOnClickListener(v -> {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, BLUETOOTH_PERMISSION_REQUEST_CODE);
                return;
            }

            try {
                if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
                    Toast.makeText(this, "강의가 종료되었습니다. (블루투스 제어)", Toast.LENGTH_SHORT).show();
                }
            } catch (SecurityException e) {
                Toast.makeText(this, "최신 안드로이드에서는 시스템 설정에서 직접 꺼야 합니다.", Toast.LENGTH_SHORT).show();
            }

            btnClassStart.setText("강의 시작");
            btnClassStart.setBackground(null);
            btnClassEnd.setText("✓ 강의 종료");
            btnClassEnd.setBackgroundResource(R.drawable.bg_pill_right_filled);
        });

        setupStudentButtons(tvStatus1, ivStatusIcon1,
                findViewById(R.id.btn_present_1), findViewById(R.id.btn_late_1),
                findViewById(R.id.btn_yugo_1), findViewById(R.id.btn_absent_1),
                findViewById(R.id.btn_present_1), "출석", "#4CAF50", R.drawable.ic_status_present);

        setupStudentButtons(tvStatus2, ivStatusIcon2,
                findViewById(R.id.btn_present_2), findViewById(R.id.btn_late_2),
                findViewById(R.id.btn_yugo_2), findViewById(R.id.btn_absent_2),
                findViewById(R.id.btn_late_2), "지각", "#FF7043", R.drawable.ic_status_late);

        setupStudentButtons(tvStatus3, ivStatusIcon3,
                findViewById(R.id.btn_present_3), findViewById(R.id.btn_late_3),
                findViewById(R.id.btn_yugo_3), findViewById(R.id.btn_absent_3),
                findViewById(R.id.btn_absent_3), "결석", "#D32F2F", R.drawable.ic_status_absent);
    }

    private void setupStudentButtons(TextView tvStatus, ImageView statusIconView,
                                     TextView btnPresent, TextView btnLate,
                                     TextView btnYugo, TextView btnAbsent,
                                     TextView defaultBtn, String defaultText, String defaultColorHex, int defaultIconRes) {

        if (tvStatus == null || statusIconView == null || btnPresent == null || btnLate == null || btnYugo == null || btnAbsent == null) return;

        updateAttendanceState(tvStatus, statusIconView, defaultBtn,
                (defaultBtn == btnPresent ? btnLate : btnPresent),
                btnYugo, btnAbsent, defaultText, defaultColorHex, defaultIconRes);

        btnPresent.setOnClickListener(v ->
                updateAttendanceState(tvStatus, statusIconView, btnPresent, btnLate, btnYugo, btnAbsent, "출석", "#4CAF50", R.drawable.ic_status_present));

        btnLate.setOnClickListener(v ->
                updateAttendanceState(tvStatus, statusIconView, btnLate, btnPresent, btnYugo, btnAbsent, "지각", "#FF7043", R.drawable.ic_status_late));

        btnYugo.setOnClickListener(v ->
                updateAttendanceState(tvStatus, statusIconView, btnYugo, btnPresent, btnLate, btnAbsent, "유고", "#9E9E9E", R.drawable.ic_status_yugo));

        btnAbsent.setOnClickListener(v ->
                updateAttendanceState(tvStatus, statusIconView, btnAbsent, btnPresent, btnLate, btnYugo, "결석", "#D32F2F", R.drawable.ic_status_absent));
    }

    private void updateAttendanceState(TextView statusText, ImageView statusIconView, TextView selectedBtn,
                                       TextView un1, TextView un2, TextView un3,
                                       String text, String colorHex, int iconRes) {
        statusText.setText(text);
        statusText.setTextColor(Color.parseColor(colorHex));

        statusIconView.setImageResource(iconRes);
        statusIconView.setVisibility(View.VISIBLE);

        selectedBtn.setAlpha(1.0f);
        un1.setAlpha(0.3f);
        un2.setAlpha(0.3f);
        un3.setAlpha(0.3f);

        updateStatistics();
    }

    private void updateStatistics() {
        int present = 0, late = 0, yugo = 0, absent = 0;

        TextView[] statuses = {tvStatus1, tvStatus2, tvStatus3};

        for (TextView tv : statuses) {
            if (tv == null) continue;
            String text = tv.getText().toString();

            if (text.equals("출석")) present++;
            else if (text.equals("지각")) late++;
            else if (text.equals("유고")) yugo++;
            else if (text.equals("결석")) absent++;
        }

        if (tvCountPresent != null) tvCountPresent.setText("출석: " + present);
        if (tvCountLate != null) tvCountLate.setText("지각: " + late);
        if (tvCountYugo != null) tvCountYugo.setText("유고: " + yugo);
        if (tvCountAbsent != null) tvCountAbsent.setText("결석: " + absent);
    }
}