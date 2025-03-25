package com.example.prm392_final_project.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prm392_final_project.R;
import com.example.prm392_final_project.Model.Course;
import com.example.prm392_final_project.Ui.CourseDetailActivity;
import com.example.prm392_final_project.Ui.OrderActivity;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private List<Course> courses;

    public CourseAdapter(List<Course> courses) {
        this.courses = courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_fragment_home_item_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.courseName.setText(course.getName());
        holder.courseDescription.setText(course.getDescription());
        holder.coursePrice.setText(course.getPrice() + " VND");

        Glide.with(holder.itemView.getContext())
                .load(course.getImageUrl())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.courseImage);

        // Handle item click
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), OrderActivity.class);
            intent.putExtra("courseId", course.getId());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return courses != null ? courses.size() : 0;
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView courseName, courseDescription, coursePrice;
        ImageView courseImage;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.tvCourseName);
            courseDescription = itemView.findViewById(R.id.tvCourseDescription);
            coursePrice = itemView.findViewById(R.id.tvCoursePrice);
            courseImage = itemView.findViewById(R.id.ivCourseImage);
        }
    }
}
