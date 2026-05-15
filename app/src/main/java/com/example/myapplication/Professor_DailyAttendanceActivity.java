package com.example.myapplication; // 본인 패키지명에 맞게 수정

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Professor_DailyAttendanceActivity extends AppCompatActivity {

    private TextView tvStatus1, tvStatus2, tvStatus3;
    private TextView tvCountPresent, tvCountLate, tvCountYugo, tvCountAbsent;
    private ImageView ivStatusIcon1, ivStatusIcon2, ivStatusIcon3;

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
        // ★ 현재 날짜 동기화 & 교시별 시간 처리
        // =====================================================================
        TextView tvDateExample = findViewById(R.id.tv_date_example);
        TextView tvClassIcon = findViewById(R.id.tv_class_icon);
        TextView tvClassTime = findViewById(R.id.tv_class_time);

        Calendar calendar = Calendar.getInstance();
        String dateSlash = new SimpleDateFormat("yyyy/MM/dd", Locale.KOREA).format(calendar.getTime());
        String dateDash = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(calendar.getTime());

        // ★ 수정됨: "예) "를 지우고 날짜만 출력합니다.
        if (tvDateExample != null) {
            tvDateExample.setText(dateSlash);
        }

        String passedClassNum = getIntent().getStringExtra("CLASS_NUM");

        if (passedClassNum != null) {
            tvClassIcon.setText(passedClassNum);

            if (passedClassNum.equals("1")) {
                tvClassTime.setText("첨단정보학관 406호\n" + dateDash + " 09:30");
            } else if (passedClassNum.equals("2")) {
                tvClassTime.setText("첨단정보학관 406호\n" + dateDash + " 10:30");
            } else if (passedClassNum.equals("3")) {
                tvClassTime.setText("첨단정보학관 406호\n" + dateDash + " 11:30");
            }
        } else {
            tvClassTime.setText("첨단정보학관 406호\n" + dateDash + " 09:30");
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

        if (statusIconView.getTag() != null) {
            statusIconView.removeCallbacks((Runnable) statusIconView.getTag());
        }

        Runnable hideIconAction = new Runnable() {
            @Override
            public void run() {
                statusIconView.setVisibility(View.INVISIBLE);
            }
        };

        statusIconView.setTag(hideIconAction);
        statusIconView.postDelayed(hideIconAction, 3000);

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