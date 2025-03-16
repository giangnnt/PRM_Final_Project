package com.example.prm392_final_project.ui.fragment.auth;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.prm392_final_project.R;
import com.example.prm392_final_project.model.auth.TokenData;
import com.example.prm392_final_project.repository.AuthRepository;
import com.example.prm392_final_project.ui.MainActivity;
import com.example.prm392_final_project.viewmodel.AuthViewModel;

public class LoginFragment extends Fragment {
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private AuthRepository authRepository;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_auth_fragment_login, container, false);

        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        btnLogin = view.findViewById(R.id.btnLogin);

        authRepository = new AuthRepository();

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Email and password cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            loginUser(email, password);
        });

        return view;
    }
    private void loginUser(String email, String password) {
        authRepository.login(email, password).observe(getViewLifecycleOwner(), tokenData -> {
            if (tokenData != null) {
                // Save access token to SharedPreferences
                SharedPreferences preferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("access_token", tokenData.getAccessToken());
                editor.apply();

                // Navigate to MainActivity
                Intent intent = new Intent(requireActivity(), MainActivity.class);
                startActivity(intent);
                requireActivity().finish();
            } else {
                Toast.makeText(requireContext(), "Login failed. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
