package com.example.prm392_final_project.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prm392_final_project.R;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.prm392_final_project.R;
import com.example.prm392_final_project.ui.fragment.auth.LoginFragment;
import com.example.prm392_final_project.ui.fragment.auth.RegisterFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class AuthActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private AuthPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        // Setup ViewPager2 with Adapter
        pagerAdapter = new AuthPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        // Link TabLayout with ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0) {
                tab.setText("Login");
            } else {
                tab.setText("Register");
            }
        }).attach();
    }

    private static class AuthPagerAdapter extends FragmentStateAdapter {

        private final List<Fragment> fragmentList = new ArrayList<>();

        public AuthPagerAdapter(@NonNull AppCompatActivity fragmentActivity) {
            super(fragmentActivity);
            fragmentList.add(new LoginFragment());
            fragmentList.add(new RegisterFragment());
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return fragmentList.size();
        }
    }
}