package com.example.prm392_final_project.ui.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_final_project.R;

import java.util.ArrayList;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prm392_final_project.R;
import com.example.prm392_final_project.adapter.CourseAdapter;
import com.example.prm392_final_project.model.Course;
import com.example.prm392_final_project.viewmodel.CourseViewModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private CourseViewModel courseViewModel;
    private RecyclerView recyclerView;
    private CourseAdapter courseAdapter;
    private ImageView featuredCourseImage;
    private androidx.appcompat.widget.SearchView searchView; // ✅ FIX HERE
    private List<Course> allCourses = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_fragment_home, container, false);

        // Initialize views
        searchView = view.findViewById(R.id.search_bar); // ✅ FIX HERE
        featuredCourseImage = view.findViewById(R.id.iv_featured_course);
        recyclerView = view.findViewById(R.id.recycler_courses);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        courseAdapter = new CourseAdapter(new ArrayList<>());
        recyclerView.setAdapter(courseAdapter);

        // ViewModel Initialization
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        // Observe courses from ViewModel
        courseViewModel.getCourses().observe(getViewLifecycleOwner(), courses -> {
            if (courses != null && !courses.isEmpty()) {
                allCourses = courses; // Store all courses for search
                courseAdapter.setCourses(courses);

                // Set first course as featured
                Glide.with(this)
                        .load(courses.get(0).getImageUrl())
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_foreground)
                        .into(featuredCourseImage);
            } else {
                Log.e("HOME_FRAGMENT", "No courses found");
            }
        });

        // Setup search functionality
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterCourses(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterCourses(newText);
                return true;
            }
        });

        return view;
    }

    private void filterCourses(String query) {
        if (TextUtils.isEmpty(query)) {
            courseAdapter.setCourses(allCourses); // Reset to all courses if query is empty
        } else {
            List<Course> filteredList = new ArrayList<>();
            for (Course course : allCourses) {
                if (course.getName().toLowerCase().contains(query.toLowerCase()) ||
                        course.getDescription().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(course);
                }
            }
            courseAdapter.setCourses(filteredList);
        }
    }
}