package com.example.prm392_final_project.Ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.prm392_final_project.Model.Course;
import com.example.prm392_final_project.R;
import com.example.prm392_final_project.Viewmodel.CourseViewModel;

import java.util.UUID;

public class OrderActivity extends AppCompatActivity {
    private Course course;
    private TextView courseNameTextView;
    private TextView courseDescriptionTextView;
    private TextView coursePriceTextView;
    private ImageView courseImageView;
    private Button btnScanQr;

    private CourseViewModel courseViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Check if user is logged in
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String token = preferences.getString("access_token", null);
        if (token == null) {
            startActivity(new Intent(this, AuthActivity.class));
            finish();
            return;
        }

        // Get the course ID from the intent
        String courseIdString = getIntent().getStringExtra("courseId");
        if (courseIdString != null) {
            UUID courseId = UUID.fromString(courseIdString); // Chuyển từ chuỗi thành UUID

            // Init View Model
            courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

            // Observe the course from the ViewModel
            courseViewModel.getCourseById(courseId).observe(this, course -> {
                if (course != null) {
                    this.course = course;
                    courseNameTextView = findViewById(R.id.tvCourseName);
                    courseDescriptionTextView = findViewById(R.id.tvCourseDescription);
                    coursePriceTextView = findViewById(R.id.tvCoursePrice);
                    courseImageView = findViewById(R.id.ivCourseImage);

                    if (course != null) {
                        courseNameTextView.setText(course.getName());
                        courseDescriptionTextView.setText(course.getDescription());
                        coursePriceTextView.setText("Price: " + course.getPrice() + " VND");
                        // Load course image using Glide or any other image loading library
                        Glide.with(this).load(course.getImageUrl()).into(courseImageView);
                    }
                }
            });
        }

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order);

        btnScanQr = findViewById(R.id.btnScanQr);

        btnScanQr.setOnClickListener(v -> {
            Intent intent = new Intent(OrderActivity.this, QrCodeActivity.class);
            intent.putExtra("courseId", course.getId());
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}