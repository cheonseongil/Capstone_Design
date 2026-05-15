package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Professor_AttendanceManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.professor_attendance_management);

        if (findViewById(R.id.main) != null) {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        ImageView btnBack = findViewById(R.id.btn_back);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> {
                finish();
            });
        }

        TextView tvDate1 = findViewById(R.id.tv_date_1);
        TextView tvDate2 = findViewById(R.id.tv_date_2);
        TextView tvDate3 = findViewById(R.id.tv_date_3);

        Calendar calendar = Calendar.getInstance();
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(calendar.getTime());
        String locationText = "첨단정보학관 406호 " + currentDate;

        if (tvDate1 != null) tvDate1.setText(locationText);
        if (tvDate2 != null) tvDate2.setText(locationText);
        if (tvDate3 != null) tvDate3.setText(locationText);

        // ==========================================
        // ★ 수정됨: Intent.putExtra로 1, 2, 3 숫자를 함께 보냅니다!
        // ==========================================
        ConstraintLayout clCourse1 = findViewById(R.id.cl_course_1);
        if (clCourse1 != null) {
            clCourse1.setOnClickListener(v -> {
                Intent intent = new Intent(Professor_AttendanceManagementActivity.this, Professor_StudentListActivity.class);
                intent.putExtra("CLASS_NUM", "1"); // 숫자 1 배달
                startActivity(intent);
            });
        }

        ConstraintLayout clCourse2 = findViewById(R.id.cl_course_2);
        if (clCourse2 != null) {
            clCourse2.setOnClickListener(v -> {
                Intent intent = new Intent(Professor_AttendanceManagementActivity.this, Professor_StudentListActivity.class);
                intent.putExtra("CLASS_NUM", "2"); // 숫자 2 배달
                startActivity(intent);
            });
        }

        ConstraintLayout clCourse3 = findViewById(R.id.cl_course_3);
        if (clCourse3 != null) {
            clCourse3.setOnClickListener(v -> {
                Intent intent = new Intent(Professor_AttendanceManagementActivity.this, Professor_StudentListActivity.class);
                intent.putExtra("CLASS_NUM", "3"); // 숫자 3 배달
                startActivity(intent);
            });
        }
    }
}