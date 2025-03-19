package com.example.prm392_final_project.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.prm392_final_project.DataModels.Course;
import com.example.prm392_final_project.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private List<Course> courses;

    public CourseAdapter(List<Course> courses) {
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.courseName.setText(course.getName());
        holder.courseDescription.setText(course.getDescription());
        holder.coursePrice.setText("Price: " + course.getPrice() + " VND");
        Glide.with(holder.itemView.getContext()).load(course.getImageUrl()).into(holder.courseImage);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView courseName, courseDescription, coursePrice;
        ImageView courseImage;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.courseName);
            courseDescription = itemView.findViewById(R.id.courseDescription);
            coursePrice = itemView.findViewById(R.id.coursePrice);
            courseImage = itemView.findViewById(R.id.courseImage);
        }
    }
}

