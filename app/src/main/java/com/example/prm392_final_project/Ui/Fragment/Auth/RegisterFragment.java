package com.example.prm392_final_project.Ui.Fragment.Auth;

import android.os.Bundle;
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
import com.example.prm392_final_project.Viewmodel.AuthViewModel;



public class RegisterFragment extends Fragment {
    private AuthViewModel authViewModel;
    private EditText etName, etEmail, etPassword, etPhoneNumber, etAvatarUrl;
    private Button btnRegister;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_auth_fragment_register, container, false);

        etName = view.findViewById(R.id.etName);
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        etPhoneNumber = view.findViewById(R.id.etPhoneNumber);
        etAvatarUrl = view.findViewById(R.id.etAvatarUrl);
        btnRegister = view.findViewById(R.id.btnRegister);

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String phoneNumber = etPhoneNumber.getText().toString().trim();
            String avatarUrl = etAvatarUrl.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty() || avatarUrl.isEmpty()) {
                Toast.makeText(getActivity(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            authViewModel.register(name, email, password, phoneNumber, avatarUrl).observe(getViewLifecycleOwner(), success -> {
                if (success) {
                    Toast.makeText(getActivity(), "Registration Successful!", Toast.LENGTH_SHORT).show();
                    // Switch to login fragment
                } else {
                    Toast.makeText(getActivity(), "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            });
        });

        return view;
    }
}