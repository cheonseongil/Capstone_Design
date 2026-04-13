package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Professor_DailyAttendanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.professor_daily_attendance);

        // 1. 상단 여백 설정
        if (findViewById(R.id.main) != null) {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // 2. 뒤로 가기 버튼
        ImageView btnBack = findViewById(R.id.iv_back);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // --- [학생별 독립적인 버튼 동작 설정] ---

        // 학생 1 셋팅 (초기값을 '출석'으로 세팅)
        setupStudentButtons(
                findViewById(R.id.tv_status_1),
                findViewById(R.id.btn_present_1), findViewById(R.id.btn_late_1),
                findViewById(R.id.btn_yugo_1), findViewById(R.id.btn_absent_1),
                findViewById(R.id.btn_present_1), " ", "#4CAF50"
        );

        // 학생 2 셋팅 (XML에 _2로 ID를 추가한 후 아래 주석을 풀면 즉시 작동합니다)

        setupStudentButtons(
                findViewById(R.id.tv_status_2),
                findViewById(R.id.btn_present_2), findViewById(R.id.btn_late_2),
                findViewById(R.id.btn_yugo_2), findViewById(R.id.btn_absent_2),
                findViewById(R.id.btn_present_2), " ", "#4CAF50"
        );


        // 학생 3 셋팅 (학생 3의 초기값을 '지각'으로 하고 싶다면 이렇게 합니다)

        setupStudentButtons(
                findViewById(R.id.tv_status_3),
                findViewById(R.id.btn_present_3), findViewById(R.id.btn_late_3),
                findViewById(R.id.btn_yugo_3), findViewById(R.id.btn_absent_3),
                findViewById(R.id.btn_late_3), " ", "#FF7043"
        );


        // 학생 4, 5가 있다면 위와 똑같은 방식으로 한 블록씩만 추가해주면 됩니다!
    }

    /**
     * 특정 학생 한 명의 버튼 클릭 이벤트를 일괄 설정하는 도우미 메서드
     */
    private void setupStudentButtons(TextView tvStatus,
                                     TextView btnPresent, TextView btnLate,
                                     TextView btnYugo, TextView btnAbsent,
                                     TextView defaultBtn, String defaultText, String defaultColorHex) {

        // 해당 학생의 뷰를 찾지 못했다면(XML에 ID가 없다면) 에러 방지를 위해 종료
        if (tvStatus == null || btnPresent == null || btnLate == null || btnYugo == null || btnAbsent == null) return;

        // 화면이 처음 켜졌을 때 보여줄 초기 상태 적용
        updateAttendanceState(tvStatus, defaultBtn,
                (defaultBtn == btnPresent ? btnLate : btnPresent), // 선택 안 된 버튼들
                btnYugo, btnAbsent, defaultText, defaultColorHex);

        // 출석 버튼을 눌렀을 때
        btnPresent.setOnClickListener(v ->
                updateAttendanceState(tvStatus, btnPresent, btnLate, btnYugo, btnAbsent, "출석", "#4CAF50"));

        // 지각 버튼을 눌렀을 때
        btnLate.setOnClickListener(v ->
                updateAttendanceState(tvStatus, btnLate, btnPresent, btnYugo, btnAbsent, "지각", "#FF7043"));

        // 유고 버튼을 눌렀을 때
        btnYugo.setOnClickListener(v ->
                updateAttendanceState(tvStatus, btnYugo, btnPresent, btnLate, btnAbsent, "유고", "#9E9E9E"));

        // 결석 버튼을 눌렀을 때
        btnAbsent.setOnClickListener(v ->
                updateAttendanceState(tvStatus, btnAbsent, btnPresent, btnLate, btnYugo, "결석", "#D32F2F"));
    }

    /**
     * 버튼의 시각적 효과와 글씨를 업데이트하는 메서드
     */
    private void updateAttendanceState(TextView statusText, TextView selectedBtn,
                                       TextView un1, TextView un2, TextView un3,
                                       String text, String colorHex) {
        // 상태 글씨 및 색상 변경
        statusText.setText(text);
        statusText.setTextColor(Color.parseColor(colorHex));

        // 선택된 버튼은 불투명하게(100%), 나머지는 반투명하게(30%)
        selectedBtn.setAlpha(1.0f);
        un1.setAlpha(0.3f);
        un2.setAlpha(0.3f);
        un3.setAlpha(0.3f);
    }
}