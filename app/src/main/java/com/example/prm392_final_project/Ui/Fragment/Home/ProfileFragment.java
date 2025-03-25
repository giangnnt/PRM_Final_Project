package com.example.prm392_final_project.Ui.Fragment.Home;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.prm392_final_project.R;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.example.prm392_final_project.Ui.AuthActivity;
import com.example.prm392_final_project.Viewmodel.UserViewModel;

import android.util.Log;
import android.widget.Toast;

public class ProfileFragment extends Fragment {
    private UserViewModel userViewModel;
    private TextView userName, userEmail, userRole;
    private ImageView userAvatar;
    private Button btnLogout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_fragment_profile, container, false);

        userName = view.findViewById(R.id.tvUserName);
        userEmail = view.findViewById(R.id.tvUserEmail);
        userRole = view.findViewById(R.id.tvUserRole);
        userAvatar = view.findViewById(R.id.ivUserAvatar); // ImageView for avatar
        btnLogout = view.findViewById(R.id.btnLogout);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        String token = getTokenFromStorage();

        userViewModel.getUser(token).observe(getViewLifecycleOwner(), user -> {
            if (user != null) {  // `user` is already the correct object
                userName.setText(user.getName());
                userEmail.setText(user.getEmail());
                userRole.setText("Role ID: " + user.getRoleId());

                // Load avatar using Glide
                Glide.with(this)
                        .load(user.getAvatarUrl())
                        .placeholder(R.drawable.ic_profile)
                        .error(R.drawable.ic_profile)
                        .into(userAvatar);

                Log.d("PROFILE_FRAGMENT", "User Data: " + user.getName());
            } else {
                Log.e("PROFILE_FRAGMENT", "User data is null");
            }
        });

        // Logout
        btnLogout.setOnClickListener(v -> {
            logoutUser();
        });


        return view;
    }
    private String getTokenFromStorage() {
        SharedPreferences preferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        return preferences.getString("access_token", null);
    }
    private void logoutUser() {
        // Clear stored token
        SharedPreferences preferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("access_token"); // Remove token
        editor.apply();

        // Redirect to AuthActivity
        Intent intent = new Intent(getActivity(), AuthActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear all previous activities
        startActivity(intent);

        // Show message and finish
        Toast.makeText(getActivity(), "Logged out successfully", Toast.LENGTH_SHORT).show();
        requireActivity().finish();
    }
}

