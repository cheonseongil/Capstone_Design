package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Professor_StudentDetailActivity extends AppCompatActivity {

    // 전체 통계 텍스트뷰 변수
    private TextView tvTotalPresent, tvTotalLate, tvTotalYugo, tvTotalAbsent;

    // 상태를 확인할 5개의 기록 텍스트뷰 배열
    private TextView[] statusViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.professor_activity_student_detail);

        // 1. 뒤로 가기
        ImageView btnBack = findViewById(R.id.btn_back);
        if (btnBack != null) btnBack.setOnClickListener(v -> finish());

        // 2. 학생 이름, 학번 데이터 세팅
        String name = getIntent().getStringExtra("STUDENT_NAME");
        String id = getIntent().getStringExtra("STUDENT_ID");

        TextView tvName = findViewById(R.id.tv_detail_name);
        TextView tvId = findViewById(R.id.tv_detail_id);

        if (name != null) tvName.setText(name);
        if (id != null) tvId.setText(id);

        // 3. 통계용 뷰 연결
        tvTotalPresent = findViewById(R.id.tv_total_present);
        tvTotalLate = findViewById(R.id.tv_total_late);
        tvTotalYugo = findViewById(R.id.tv_total_yugo);
        tvTotalAbsent = findViewById(R.id.tv_total_absent);

        // 상태 글씨를 쉽게 세기 위해 배열로 묶어둠
        statusViews = new TextView[]{
                findViewById(R.id.tv_record_status_1),
                findViewById(R.id.tv_record_status_2),
                findViewById(R.id.tv_record_status_3),
                findViewById(R.id.tv_record_status_4),
                findViewById(R.id.tv_record_status_5)
        };

        // 4. 기록별 출석 버튼 동작 세팅 (기본값 설정)
        setupRecordButtons(statusViews[0], findViewById(R.id.btn_present_1), findViewById(R.id.btn_late_1), findViewById(R.id.btn_yugo_1), findViewById(R.id.btn_absent_1), findViewById(R.id.btn_present_1), "출석", "#4CAF50");
        setupRecordButtons(statusViews[1], findViewById(R.id.btn_present_2), findViewById(R.id.btn_late_2), findViewById(R.id.btn_yugo_2), findViewById(R.id.btn_absent_2), findViewById(R.id.btn_late_2), "지각", "#FF7043");
        setupRecordButtons(statusViews[2], findViewById(R.id.btn_present_3), findViewById(R.id.btn_late_3), findViewById(R.id.btn_yugo_3), findViewById(R.id.btn_absent_3), findViewById(R.id.btn_present_3), "출석", "#4CAF50");
        setupRecordButtons(statusViews[3], findViewById(R.id.btn_present_4), findViewById(R.id.btn_late_4), findViewById(R.id.btn_yugo_4), findViewById(R.id.btn_absent_4), findViewById(R.id.btn_absent_4), "결석", "#D32F2F");
        setupRecordButtons(statusViews[4], findViewById(R.id.btn_present_5), findViewById(R.id.btn_late_5), findViewById(R.id.btn_yugo_5), findViewById(R.id.btn_absent_5), findViewById(R.id.btn_present_5), "출석", "#4CAF50");

        // 5. 화면이 처음 켜질 때 통계를 한 번 계산해서 보여줌
        updateTotalSummary();
    }

    /**
     * 하나의 기록 박스 안에서 버튼 클릭 시 상태 글씨와 버튼 투명도를 바꿔주는 메서드
     */
    private void setupRecordButtons(TextView tvStatus,
                                    TextView btnPresent, TextView btnLate,
                                    TextView btnYugo, TextView btnAbsent,
                                    TextView defaultBtn, String defaultText, String defaultColorHex) {

        if (tvStatus == null || btnPresent == null || btnLate == null || btnYugo == null || btnAbsent == null) return;

        // 초기 화면 세팅 (이때는 통계 업데이트 안함. 어차피 onCreate 마지막에 한 번 함)
        updateStateOnly(tvStatus, defaultBtn, (defaultBtn == btnPresent ? btnLate : btnPresent), btnYugo, btnAbsent, defaultText, defaultColorHex);

        // 버튼 누를 때 상태 변경 후 -> 통계 다시 계산!
        btnPresent.setOnClickListener(v -> {
            updateStateOnly(tvStatus, btnPresent, btnLate, btnYugo, btnAbsent, "출석", "#4CAF50");
            updateTotalSummary(); // ★ 통계 갱신
        });

        btnLate.setOnClickListener(v -> {
            updateStateOnly(tvStatus, btnLate, btnPresent, btnYugo, btnAbsent, "지각", "#FF7043");
            updateTotalSummary(); // ★ 통계 갱신
        });

        btnYugo.setOnClickListener(v -> {
            updateStateOnly(tvStatus, btnYugo, btnPresent, btnLate, btnAbsent, "유고", "#9E9E9E");
            updateTotalSummary(); // ★ 통계 갱신
        });

        btnAbsent.setOnClickListener(v -> {
            updateStateOnly(tvStatus, btnAbsent, btnPresent, btnLate, btnYugo, "결석", "#D32F2F");
            updateTotalSummary(); // ★ 통계 갱신
        });
    }

    /**
     * 실제로 뷰(글씨, 색깔, 투명도)를 변경하는 로직
     */
    private void updateStateOnly(TextView statusText, TextView selectedBtn, TextView un1, TextView un2, TextView un3, String text, String colorHex) {
        statusText.setText(text);
        statusText.setTextColor(Color.parseColor(colorHex));

        if (selectedBtn != null) selectedBtn.setAlpha(1.0f);
        if (un1 != null) un1.setAlpha(0.3f);
        if (un2 != null) un2.setAlpha(0.3f);
        if (un3 != null) un3.setAlpha(0.3f);
    }

    /**
     * ★ 추가된 기능: 5개의 기록을 싹 다 읽어서 위쪽 통계 박스의 숫자를 바꿔줍니다!
     */
    private void updateTotalSummary() {
        int presentCount = 0;
        int lateCount = 0;
        int yugoCount = 0;
        int absentCount = 0;

        // 배열로 묶어둔 5개의 상태 텍스트뷰를 하나씩 확인
        for (TextView tv : statusViews) {
            if (tv != null) {
                String status = tv.getText().toString().trim();
                if (status.equals("출석")) {
                    presentCount++;
                } else if (status.equals("지각")) {
                    lateCount++;
                } else if (status.equals("유고")) {
                    yugoCount++;
                } else if (status.equals("결석")) {
                    absentCount++;
                }
            }
        }

        // 결과값을 상단 텍스트뷰에 띄워줌
        if (tvTotalPresent != null) tvTotalPresent.setText("출석: " + presentCount);
        if (tvTotalLate != null) tvTotalLate.setText("지각: " + lateCount);
        if (tvTotalYugo != null) tvTotalYugo.setText("유고: " + yugoCount);
        if (tvTotalAbsent != null) tvTotalAbsent.setText("결석: " + absentCount);
    }
}