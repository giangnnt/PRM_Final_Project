package com.example.prm392_final_project.ui.fragment.home;
import android.content.Context;
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

import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.example.prm392_final_project.viewmodel.UserViewModel;

import android.util.Log;

import com.example.prm392_final_project.model.User;

public class ProfileFragment extends Fragment {
    private UserViewModel userViewModel;
    private TextView userName, userEmail, userRole;
    private ImageView userAvatar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_fragment_profile, container, false);

        userName = view.findViewById(R.id.tvUserName);
        userEmail = view.findViewById(R.id.tvUserEmail);
        userRole = view.findViewById(R.id.tvUserRole);
        userAvatar = view.findViewById(R.id.ivUserAvatar); // ImageView for avatar

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        String token = getTokenFromStorage();

        userViewModel.getUser(token).observe(getViewLifecycleOwner(), user -> {
            if (user != null) {  // ✅ `user` is already the correct object
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



        return view;
    }
    private String getTokenFromStorage() {
        SharedPreferences preferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        return preferences.getString("access_token", null);
    }
}

