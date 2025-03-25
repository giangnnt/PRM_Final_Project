package com.example.prm392_final_project.Ui.Fragment.Auth;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.prm392_final_project.R;
import com.example.prm392_final_project.Api.ApiService;
import com.example.prm392_final_project.Api.RetrofitClient;
import com.example.prm392_final_project.Model.ResponseModel;
import com.example.prm392_final_project.Model.Auth.LoginRequest;
import com.example.prm392_final_project.Model.Auth.TokenData;
import com.example.prm392_final_project.Repository.AuthRepository;
import com.example.prm392_final_project.Ui.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        ApiService apiService = RetrofitClient.getApiService();
        Call<ResponseModel<TokenData>> call = apiService.login(new LoginRequest(email, password));

        call.enqueue(new Callback<ResponseModel<TokenData>>() {
            @Override
            public void onResponse(Call<ResponseModel<TokenData>> call, Response<ResponseModel<TokenData>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResponseModel<TokenData> apiResponse = response.body();
                    if (apiResponse.isSuccess()) {
                        TokenData loginData = apiResponse.getResult().getData();
                        String token = loginData.getAccessToken();

                        // Save token to SharedPreferences
                        SharedPreferences preferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("access_token", token);
                        editor.apply();

                        // Show success message
                        Toast.makeText(requireContext(), "Login Successful!", Toast.LENGTH_SHORT).show();

                        // Redirect to MainActivity
                        Intent intent = new Intent(requireActivity(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        Toast.makeText(requireContext(), "Login Failed: " + apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(requireContext(), "Login Request Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<TokenData>> call, Throwable t) {
                Toast.makeText(requireContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
