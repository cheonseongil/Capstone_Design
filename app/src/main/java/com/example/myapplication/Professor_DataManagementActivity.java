package com.example.myapplication; // 본인의 패키지명 확인

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Professor_DataManagementActivity extends AppCompatActivity {

    // 1. 선택할 목록(배열) 준비
    String[] semesters = {"1학년 1학기", "1학년 2학기", "2학년 1학기", "2학년 2학기", "3학년 1학기", "3학년 2학기", "4학년 1학기", "4학년 2학기"};
    String[] fileFormats = {"PDF", "한글", "워드", "엑셀"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor_activity_data_management);

        // 뒤로가기 버튼
        ImageView btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());

        // ==========================================
        // 새로 추가된 부분: 텍스트뷰 연결 및 클릭 이벤트
        // ==========================================
        TextView tvSemesterDownload = findViewById(R.id.tv_semester_download);
        TextView tvFileFormatDownload = findViewById(R.id.tv_file_format_download);
        TextView tvSemesterBackup = findViewById(R.id.tv_semester_backup);

        // 다운로드 - 학기 선택 클릭 시
        tvSemesterDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectionDialog("학기 선택", semesters, tvSemesterDownload);
            }
        });

        // 다운로드 - 파일형식 선택 클릭 시
        tvFileFormatDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectionDialog("파일형식 선택", fileFormats, tvFileFormatDownload);
            }
        });

        // 백업 - 학기 선택 클릭 시
        tvSemesterBackup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectionDialog("학기 선택", semesters, tvSemesterBackup);
            }
        });
        // ==========================================


        // 각 항목 버튼 클릭 이벤트 (기능 구현 전 Toast 메시지 띄우기)
        findViewById(R.id.btn_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 선택된 값을 가져와서 Toast에 띄워줍니다.
                String selectedSemester = tvSemesterDownload.getText().toString();
                String selectedFormat = tvFileFormatDownload.getText().toString();
                Toast.makeText(Professor_DataManagementActivity.this,
                        selectedSemester + " / " + selectedFormat + " 출결기록을 다운로드합니다.", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Professor_DataManagementActivity.this, "데이터 초기화 확인 창을 띄웁니다.", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_backup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 선택된 값을 가져와서 Toast에 띄워줍니다.
                String selectedSemester = tvSemesterBackup.getText().toString();
                Toast.makeText(Professor_DataManagementActivity.this,
                        selectedSemester + " 데이터를 백업합니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 목록 다이얼로그를 띄워주는 공통 메서드 (새로 추가됨)
     */
    private void showSelectionDialog(String title, String[] items, TextView targetTextView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setItems(items, (dialog, which) -> {
            // 사용자가 선택한 항목의 텍스트를 가져와서 텍스트뷰에 적용
            String selectedItem = items[which];
            targetTextView.setText(selectedItem);

            // 글씨 색상을 검은색으로 변경하여 선택되었음을 명확히 보여줌
            targetTextView.setTextColor(getResources().getColor(android.R.color.black, getTheme()));
        });
        builder.show();
    }
}