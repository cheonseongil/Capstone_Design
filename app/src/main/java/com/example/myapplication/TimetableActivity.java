package com.example.myapplication; // 본인 패키지명

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TimetableActivity extends AppCompatActivity {

    private RelativeLayout[] dayColumns = new RelativeLayout[5];
    private boolean isDeleteMode = false;
    private TextView btnDelete;

    // 블록에 사용할 예쁜 색상들 (네트워크, DB 등에 쓰인 색상)
    private String[] colors = {"#B2EBF2", "#81C784", "#FFF59D", "#883965", "#FFAB91"};
    private int colorIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        // 1. 뒤로가기
        findViewById(R.id.btn_back).setOnClickListener(v -> finish());

        // 2. 기둥(Column) 연결
        dayColumns[0] = findViewById(R.id.col_mon);
        dayColumns[1] = findViewById(R.id.col_tue);
        dayColumns[2] = findViewById(R.id.col_wed);
        dayColumns[3] = findViewById(R.id.col_thu);
        dayColumns[4] = findViewById(R.id.col_fri);

        // 3. 왼쪽 시간(09~18) 텍스트 채우기 (각 칸의 높이를 50dp로 설정)
        LinearLayout timeCol = findViewById(R.id.layout_time_col);
        int cellHeightPx = dpToPx(50); // 1시간당 50dp의 높이를 가짐

        for (int i = 9; i <= 18; i++) {
            TextView tv = new TextView(this);
            tv.setText(String.format("%02d", i));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(12f);
            tv.setBackgroundResource(R.drawable.bg_grid_cell);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, cellHeightPx);
            tv.setLayoutParams(params);
            timeCol.addView(tv);
        }

        // 4. 추가 버튼 클릭: 팝업창 띄우기
        findViewById(R.id.btn_add).setOnClickListener(v -> showAddCourseDialog());

        // 5. 삭제 버튼 클릭: 삭제 모드 켜기/끄기
        btnDelete = findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(v -> {
            isDeleteMode = !isDeleteMode;
            if (isDeleteMode) {
                btnDelete.setTextColor(Color.RED);
                Toast.makeText(this, "삭제할 과목을 터치하세요.", Toast.LENGTH_SHORT).show();
            } else {
                btnDelete.setTextColor(Color.BLACK);
                Toast.makeText(this, "삭제 모드 해제", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 과목 추가 팝업창 띄우는 함수
    private void showAddCourseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_course, null);
        builder.setView(dialogView);

        EditText editName = dialogView.findViewById(R.id.edit_course_name);
        Spinner spinnerDay = dialogView.findViewById(R.id.spinner_day);
        Spinner spinnerStart = dialogView.findViewById(R.id.spinner_start_time);
        Spinner spinnerEnd = dialogView.findViewById(R.id.spinner_end_time);

        builder.setPositiveButton("추가", (dialog, which) -> {
            String name = editName.getText().toString();
            int dayIndex = spinnerDay.getSelectedItemPosition(); // 0:월, 1:화 ...
            int startHour = Integer.parseInt(spinnerStart.getSelectedItem().toString());
            int endHour = Integer.parseInt(spinnerEnd.getSelectedItem().toString());

            if (name.isEmpty() || startHour >= endHour) {
                Toast.makeText(this, "시간이 잘못되었거나 이름이 비었습니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            // 블록 추가 함수 호출
            addCourseBlock(name, dayIndex, startHour, endHour);
        });
        builder.setNegativeButton("취소", null);
        builder.show();
    }

    // 실제 화면에 색상 블록을 그려주는 함수 (핵심 수학 계산)
    private void addCourseBlock(String name, int dayIndex, int startHour, int endHour) {
        int cellHeightPx = dpToPx(50); // 1시간당 50dp

        // 시작 시간을 09시 기준으로 얼마만큼 떨어져 있는지 계산 (예: 11시면 11-9 = 2칸 떨어짐)
        int topMargin = (startHour - 9) * cellHeightPx;

        // 블록의 총 길이 계산 (예: 11시~13시면 13-11 = 2칸 높이)
        int height = (endHour - startHour) * cellHeightPx;

        // 블록(TextView) 생성 및 디자인
        TextView courseBlock = new TextView(this);
        courseBlock.setText(name);
        courseBlock.setGravity(Gravity.CENTER);
        courseBlock.setTextColor(Color.BLACK);
        courseBlock.setTextSize(14f);
        courseBlock.setTypeface(null, android.graphics.Typeface.BOLD);

        // 색상 지정 후 다음 색상으로 순서 넘기기
        courseBlock.setBackgroundColor(Color.parseColor(colors[colorIndex]));
        colorIndex = (colorIndex + 1) % colors.length;

        // 위치 및 크기 설정 (RelativeLayout LayoutParams 사용)
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, height);
        params.topMargin = topMargin;
        courseBlock.setLayoutParams(params);

        // ★ 삭제 기능: 과목 블록을 터치했을 때 삭제 모드라면 지워지도록 설정
        courseBlock.setOnClickListener(v -> {
            if (isDeleteMode) {
                ((ViewGroup) v.getParent()).removeView(v); // 화면에서 블록 제거
                Toast.makeText(this, name + " 삭제됨", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, name + " (삭제하려면 삭제 버튼을 먼저 누르세요)", Toast.LENGTH_SHORT).show();
            }
        });

        // 해당 요일 기둥에 블록 꽂아넣기!
        dayColumns[dayIndex].addView(courseBlock);
    }

    // dp 단위를 스마트폰 픽셀(px) 단위로 변환해주는 유틸 함수 (코드에서 크기 지정 시 필수)
    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}