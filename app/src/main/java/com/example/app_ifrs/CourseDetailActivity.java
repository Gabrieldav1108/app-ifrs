package com.example.app_ifrs;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CourseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_detail);

        // Get data from Intent
        String courseName = getIntent().getStringExtra("courseName");
        String courseType = getIntent().getStringExtra("courseType");

        // Set up views
        TextView tvCourseName = findViewById(R.id.tvCourseName);
        TextView tvCourseType = findViewById(R.id.tvCourseType);
        TextView tvDescription = findViewById(R.id.tvDescription);
        Button btnBack = findViewById(R.id.btnBack);

        // Set data
        tvCourseName.setText(courseName);
        tvCourseType.setText("Category: " + courseType);

        // Set description based on course
        if (courseName.contains("Administration Technician")) {
            tvDescription.setText("This technical course prepares students for administrative roles in companies...");
        } else if (courseName.contains("Agropecuária")) {
            tvDescription.setText("Technical training for agricultural production and animal husbandry...");
        } else if (courseName.contains("Técnico em Recursos Humanos")) {
            tvDescription.setText("Technical training for agricultural production and animal husbandry...");
        }

        // Back button
        btnBack.setOnClickListener(v -> finish());
    }
}
