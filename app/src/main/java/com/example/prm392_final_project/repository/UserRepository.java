package com.example.prm392_final_project.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.prm392_final_project.api.ApiService;
import com.example.prm392_final_project.api.RetrofitClient;
import com.example.prm392_final_project.model.ResponseModel;
import com.example.prm392_final_project.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;

public class UserRepository {
    private final ApiService apiService;

    public UserRepository() {
        apiService = RetrofitClient.getApiService();
    }

    public LiveData<User> getUser(String token) {
        MutableLiveData<User> userData = new MutableLiveData<>();

        apiService.getUser("Bearer " + token).enqueue(new Callback<ResponseModel<User>>() {
            @Override
            public void onResponse(Call<ResponseModel<User>> call, Response<ResponseModel<User>> response) {
                if (response.isSuccessful() && response.body() != null
                        && response.body().getResult() != null
                        && response.body().getResult().getData() != null) {  // ✅ Fix: Access `data`

                    User user = response.body().getResult().getData(); // ✅ Correctly get data
                    userData.setValue(user);
                    Log.d("USER_SUCCESS", "User: " + user.getName());
                } else {
                    Log.e("USER_ERROR", "Response Failed: " + response.code());
                    userData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<User>> call, Throwable t) {
                Log.e("USER_ERROR", "API Call Failed: " + t.getMessage());
                userData.setValue(null);
            }
        });

        return userData;
    }


}
