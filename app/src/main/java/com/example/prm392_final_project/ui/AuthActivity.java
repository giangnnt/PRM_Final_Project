package com.example.prm392_final_project.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prm392_final_project.R;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.prm392_final_project.R;
import com.example.prm392_final_project.ui.fragment.auth.LoginFragment;
import com.example.prm392_final_project.ui.fragment.auth.RegisterFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AuthActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if user is already logged in
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String token = preferences.getString("access_token", null);
        if (token != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_auth);

        BottomNavigationView bottomNavigationView = findViewById(R.id.auth_bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            if (item.getItemId() == R.id.nav_login) {
                selectedFragment = new LoginFragment();
            } else if (item.getItemId() == R.id.nav_register) {
                selectedFragment = new RegisterFragment();
            }
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.auth_fragment_container, selectedFragment).commit();
            }
            return true;
        });

        // Default to LoginFragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.auth_fragment_container, new LoginFragment()).commit();
        }
    }
}