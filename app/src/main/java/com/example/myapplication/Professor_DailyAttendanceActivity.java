package com.example.myapplication; // 본인 패키지명에 맞게 수정

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View; // ★ View 기능 임포트 완료
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

    // ★ 추가됨: 학생 프로필 사진 위에 표시될 아이콘 이미지 뷰 변수
    private ImageView ivStatusIcon1, ivStatusIcon2, ivStatusIcon3;

    // 블루투스 권한 요청 시 사용할 고유 번호
    private static final int BLUETOOTH_PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.professor_daily_attendance);

        // 상단 여백 설정
        if (findViewById(R.id.main) != null) {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // 뒤로 가기 버튼
        ImageView btnBack = findViewById(R.id.iv_back);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // --- 뷰(View) 아이디 연결 ---
        tvStatus1 = findViewById(R.id.tv_status_1);
        tvStatus2 = findViewById(R.id.tv_status_2);
        tvStatus3 = findViewById(R.id.tv_status_3);

        tvCountPresent = findViewById(R.id.tv_count_present);
        tvCountLate = findViewById(R.id.tv_count_late);
        tvCountYugo = findViewById(R.id.tv_count_yugo);
        tvCountAbsent = findViewById(R.id.tv_count_absent);

        // ★ 추가됨: XML에 만들어 둔 상태 아이콘 뷰 연결
        ivStatusIcon1 = findViewById(R.id.iv_status_icon_1);
        ivStatusIcon2 = findViewById(R.id.iv_status_icon_2);
        ivStatusIcon3 = findViewById(R.id.iv_status_icon_3);

        // --- 강의 활성화 버튼 동작 설정 (블루투스 연동) ---
        TextView btnClassStart = findViewById(R.id.btn_class_start);
        TextView btnClassEnd = findViewById(R.id.btn_class_end);

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // [강의 시작] 버튼 클릭 시
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

            // 시각적 버튼 효과 변경
            btnClassStart.setText("✓ 강의 시작");
            btnClassStart.setBackgroundResource(R.drawable.bg_pill_left_filled);
            btnClassEnd.setText("강의 종료");
            btnClassEnd.setBackground(null);
        });

        // [강의 종료] 버튼 클릭 시
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

            // 시각적 버튼 효과 변경
            btnClassStart.setText("강의 시작");
            btnClassStart.setBackground(null);
            btnClassEnd.setText("✓ 강의 종료");
            btnClassEnd.setBackgroundResource(R.drawable.bg_pill_right_filled);
        });

        // --- [학생별 독립적인 버튼 동작 설정] ---
        // ★ 변경됨: 매개변수로 ivStatusIcon(아이콘 뷰)와 기본 이미지 아이디(R.drawable...)를 전달

        // 학생 1 (기본: 출석)
        setupStudentButtons(tvStatus1, ivStatusIcon1,
                findViewById(R.id.btn_present_1), findViewById(R.id.btn_late_1),
                findViewById(R.id.btn_yugo_1), findViewById(R.id.btn_absent_1),
                findViewById(R.id.btn_present_1), "출석", "#4CAF50", R.drawable.ic_status_present);

        // 학생 2 (기본: 지각)
        setupStudentButtons(tvStatus2, ivStatusIcon2,
                findViewById(R.id.btn_present_2), findViewById(R.id.btn_late_2),
                findViewById(R.id.btn_yugo_2), findViewById(R.id.btn_absent_2),
                findViewById(R.id.btn_late_2), "지각", "#FF7043", R.drawable.ic_status_late);

        // 학생 3 (기본: 결석)
        setupStudentButtons(tvStatus3, ivStatusIcon3,
                findViewById(R.id.btn_present_3), findViewById(R.id.btn_late_3),
                findViewById(R.id.btn_yugo_3), findViewById(R.id.btn_absent_3),
                findViewById(R.id.btn_absent_3), "결석", "#D32F2F", R.drawable.ic_status_absent);
    }

    // ★ 변경됨: statusIconView(이미지뷰)와 defaultIconRes(아이콘 리소스) 매개변수 추가
    private void setupStudentButtons(TextView tvStatus, ImageView statusIconView,
                                     TextView btnPresent, TextView btnLate,
                                     TextView btnYugo, TextView btnAbsent,
                                     TextView defaultBtn, String defaultText, String defaultColorHex, int defaultIconRes) {

        if (tvStatus == null || statusIconView == null || btnPresent == null || btnLate == null || btnYugo == null || btnAbsent == null) return;

        // 처음 화면 켜질 때 기본 상태 및 아이콘 설정
        updateAttendanceState(tvStatus, statusIconView, defaultBtn,
                (defaultBtn == btnPresent ? btnLate : btnPresent),
                btnYugo, btnAbsent, defaultText, defaultColorHex, defaultIconRes);

        // 출석 클릭 시
        btnPresent.setOnClickListener(v ->
                updateAttendanceState(tvStatus, statusIconView, btnPresent, btnLate, btnYugo, btnAbsent, "출석", "#4CAF50", R.drawable.ic_status_present));

        // 지각 클릭 시
        btnLate.setOnClickListener(v ->
                updateAttendanceState(tvStatus, statusIconView, btnLate, btnPresent, btnYugo, btnAbsent, "지각", "#FF7043", R.drawable.ic_status_late));

        // 유고 클릭 시
        btnYugo.setOnClickListener(v ->
                updateAttendanceState(tvStatus, statusIconView, btnYugo, btnPresent, btnLate, btnAbsent, "유고", "#9E9E9E", R.drawable.ic_status_yugo));

        // 결석 클릭 시
        btnAbsent.setOnClickListener(v ->
                updateAttendanceState(tvStatus, statusIconView, btnAbsent, btnPresent, btnLate, btnYugo, "결석", "#D32F2F", R.drawable.ic_status_absent));
    }

    // ★ 변경됨: statusIconView(이미지뷰)와 iconRes(적용할 아이콘) 매개변수 추가
    private void updateAttendanceState(TextView statusText, ImageView statusIconView, TextView selectedBtn,
                                       TextView un1, TextView un2, TextView un3,
                                       String text, String colorHex, int iconRes) {
        // 상태 글씨 및 색상 변경
        statusText.setText(text);
        statusText.setTextColor(Color.parseColor(colorHex));

        // ★ 핵심: 프로필 위에 겹쳐진 아이콘 이미지 변경 후 보이게 설정
        statusIconView.setImageResource(iconRes);
        statusIconView.setVisibility(View.VISIBLE);

        // 버튼 투명도 조절
        selectedBtn.setAlpha(1.0f);
        un1.setAlpha(0.3f);
        un2.setAlpha(0.3f);
        un3.setAlpha(0.3f);

        // 통계 업데이트
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