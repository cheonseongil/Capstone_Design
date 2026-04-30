package com.example.myapplication; // 본인 패키지명 확인 필수!

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Professor_AttendanceRateActivity extends AppCompatActivity {

    private final int HOUR_HEIGHT_DP = 60;
    private int hourHeightPx;
    private final int START_HOUR = 9;
    private final int END_HOUR = 21;

    private LinearLayout layoutCurrentTime;
    private TextView tvCurrentTime;
    private Handler timerHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor_attendance_rate);

        ImageView btnBack = findViewById(R.id.btn_back);
        if(btnBack != null) btnBack.setOnClickListener(v -> finish());

        hourHeightPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, HOUR_HEIGHT_DP, getResources().getDisplayMetrics());

        layoutCurrentTime = findViewById(R.id.layout_current_time);
        tvCurrentTime = findViewById(R.id.tv_current_time);

        drawTimetableGrid();

        String courseName = "캡스톤 디자인\n첨단정보학관 406호";
        String bgColor = "#E8E2FF";
        String textColor = "#5C4599";

        // ★ 수정됨: 맨 마지막에 "1", "2", "3" 이라는 교시 데이터를 추가로 넘겨줍니다.
        addCourseBlock(courseName, 9, 30, 10, 20, bgColor, textColor, "1");
        addCourseBlock(courseName, 10, 30, 11, 20, bgColor, textColor, "2");
        addCourseBlock(courseName, 11, 30, 12, 20, bgColor, textColor, "3");

        startCurrentTimeUpdater();
    }

    private void drawTimetableGrid() {
        LinearLayout timeColumn = findViewById(R.id.ll_time_column);
        LinearLayout gridLines = findViewById(R.id.ll_grid_lines);

        for (int i = START_HOUR; i <= END_HOUR; i++) {
            TextView tvTime = new TextView(this);
            String amPm = (i < 12) ? "오전" : "오후";
            int displayHour = (i > 12) ? i - 12 : i;
            if (i == 12) { amPm = ""; displayHour = -1; tvTime.setText("정오"); }
            else { tvTime.setText(amPm + " " + displayHour + "시"); }

            tvTime.setTextColor(Color.parseColor("#666666"));
            tvTime.setTextSize(10f);
            tvTime.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
            tvTime.setPadding(0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, -6, getResources().getDisplayMetrics()), 0, 0);

            LinearLayout.LayoutParams timeParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, hourHeightPx);
            tvTime.setLayoutParams(timeParams);
            timeColumn.addView(tvTime);

            View line = new View(this);
            GradientDrawable border = new GradientDrawable();
            border.setColor(Color.TRANSPARENT);
            border.setStroke(1, Color.parseColor("#E0E0E0"));
            line.setBackground(border);

            LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, hourHeightPx);
            line.setLayoutParams(lineParams);
            gridLines.addView(line);
        }
    }

    // ★ 수정됨: 매개변수 맨 끝에 String classNum 추가
    private void addCourseBlock(String title, int startH, int startM, int endH, int endM, String bgColorHex, String textColorHex, String classNum) {
        RelativeLayout eventArea = findViewById(R.id.rl_event_area);

        int startTotalMinutes = (startH - START_HOUR) * 60 + startM;
        int durationMinutes = ((endH * 60) + endM) - ((startH * 60) + startM);

        int topMarginPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, startTotalMinutes, getResources().getDisplayMetrics());
        int heightPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, durationMinutes, getResources().getDisplayMetrics());

        TextView courseBlock = new TextView(this);
        courseBlock.setText(title);
        courseBlock.setTextColor(Color.parseColor(textColorHex));
        courseBlock.setTextSize(12f);
        courseBlock.setTypeface(null, android.graphics.Typeface.BOLD);
        courseBlock.setPadding(24, 10, 24, 10);
        courseBlock.setElevation(4f);

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.parseColor(bgColorHex));
        bg.setCornerRadius(16f);
        courseBlock.setBackground(bg);

        courseBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Professor_AttendanceRateActivity.this, Professor_DailyAttendanceActivity.class);
                // ★ 핵심: 다음 화면으로 넘어갈 때 "CLASS_NUM"이라는 이름표를 붙여서 숫자를 택배로 보냅니다!
                intent.putExtra("CLASS_NUM", classNum);
                startActivity(intent);
            }
        });

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, heightPx);
        params.topMargin = topMarginPx;
        params.leftMargin = 16;
        params.rightMargin = 32;

        eventArea.addView(courseBlock, params);
    }

    private void startCurrentTimeUpdater() {
        Runnable updateRunnable = new Runnable() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                int currentMinute = calendar.get(Calendar.MINUTE);

                SimpleDateFormat sdf = new SimpleDateFormat("a hh:mm", Locale.KOREA);
                tvCurrentTime.setText(sdf.format(calendar.getTime()));

                if (currentHour >= START_HOUR && currentHour < END_HOUR) {
                    int elapsedMinutes = (currentHour - START_HOUR) * 60 + currentMinute;
                    float yPositionPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, elapsedMinutes, getResources().getDisplayMetrics());
                    float offsetPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());

                    layoutCurrentTime.setTranslationY(yPositionPx - offsetPx);
                    layoutCurrentTime.setVisibility(View.VISIBLE);
                } else {
                    layoutCurrentTime.setVisibility(View.INVISIBLE);
                }

                timerHandler.postDelayed(this, 60000);
            }
        };
        updateRunnable.run();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timerHandler.removeCallbacksAndMessages(null);
    }
}