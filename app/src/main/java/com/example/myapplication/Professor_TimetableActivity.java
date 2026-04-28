package com.example.myapplication; // 본인 패키지명으로 확인!

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Professor_TimetableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // 위에서 만든 XML 파일명으로 수정 (예: R.layout.professor_activity_timetable)
        setContentView(R.layout.professor_activity_timetable);

        // 1. 뒤로 가기 버튼 설정
        ImageView btnBack = findViewById(R.id.btn_back);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // 2. 시간표 1칸(1시간)의 높이를 50dp로 설정 (픽셀로 변환)
        int hourHeightDp = 50;
        int hourHeightPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, hourHeightDp, getResources().getDisplayMetrics());

        // 3. 왼쪽 시간(09 ~ 18) 텍스트 그리기
        LinearLayout timeCol = findViewById(R.id.layout_time_col);
        for (int i = 9; i <= 18; i++) {
            TextView tvTime = new TextView(this);
            tvTime.setText(String.format("%02d", i)); // "09", "10" 형태로 출력
            tvTime.setGravity(Gravity.CENTER);
            tvTime.setTextColor(Color.BLACK);
            tvTime.setTextSize(10f);

            // 텍스트 아래쪽에 회색 밑줄 추가 (눈금선 효과)
            GradientDrawable borderBottom = new GradientDrawable();
            borderBottom.setColor(Color.TRANSPARENT);
            borderBottom.setStroke(1, Color.parseColor("#E0E0E0"));
            tvTime.setBackground(borderBottom);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, hourHeightPx);
            tvTime.setLayoutParams(params);
            timeCol.addView(tvTime);
        }

        // 4. 각 요일(월~금) 열에 가로 눈금선 그리기
        RelativeLayout[] dayColumns = {
                findViewById(R.id.col_mon), findViewById(R.id.col_tue),
                findViewById(R.id.col_wed), findViewById(R.id.col_thu), findViewById(R.id.col_fri)
        };

        for (RelativeLayout column : dayColumns) {
            // 시간 사이사이(총 9개)에 가로줄(1px) 생성
            for (int i = 1; i < 10; i++) {
                View line = new View(this);
                line.setBackgroundColor(Color.parseColor("#E0E0E0")); // 연한 회색 눈금
                RelativeLayout.LayoutParams lineParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2);
                lineParams.topMargin = i * hourHeightPx;
                column.addView(line, lineParams);
            }
        }

        // ==================================================
        // ★ 5. 목요일에 '캡스톤' 과목 (09:00 ~ 12:00, 3시간) 배치하기
        // ==================================================
        RelativeLayout colThu = findViewById(R.id.col_thu); // 목요일 열 찾기

        TextView capstoneBlock = new TextView(this);
        capstoneBlock.setText("캡스톤");
        capstoneBlock.setGravity(Gravity.CENTER);
        capstoneBlock.setTypeface(null, Typeface.BOLD);
        capstoneBlock.setTextColor(Color.BLACK);

        // 노란색 배경과 진한 회색 테두리 설정
        GradientDrawable bgShape = new GradientDrawable();
        bgShape.setColor(Color.parseColor("#FFF59D")); // 연한 노란색
        bgShape.setStroke(2, Color.parseColor("#BDBDBD")); // 테두리 두께 2, 회색
        capstoneBlock.setBackground(bgShape);

        // 높이와 위치 설정
        // 09시 시작이므로 topMargin = 0
        // 09시부터 12시까지 3시간 분량이므로 높이 = 1시간(hourHeightPx) * 3
        RelativeLayout.LayoutParams blockParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                hourHeightPx * 3
        );
        blockParams.topMargin = 0;

        // 블록 추가
        colThu.addView(capstoneBlock, blockParams);
    }
}