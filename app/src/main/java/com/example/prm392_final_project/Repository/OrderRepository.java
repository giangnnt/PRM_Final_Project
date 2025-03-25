package com.example.prm392_final_project.Repository;

import android.content.Intent;
import android.content.SharedPreferences;
import android.telecom.Call;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.prm392_final_project.Api.ApiService;
import com.example.prm392_final_project.Api.RetrofitClient;
import com.example.prm392_final_project.Model.PaymentResponse;
import com.example.prm392_final_project.Model.ResponseModel;
import com.example.prm392_final_project.Ui.AuthActivity;
import com.google.android.gms.common.api.Response;

import java.util.UUID;

import retrofit2.Callback;

public class OrderRepository {
    private final ApiService apiService;

    public OrderRepository() {
        apiService = RetrofitClient.getApiService();

    }
    public LiveData<PaymentResponse> GetPaymentResponse(String token, UUID courseId){
        MutableLiveData<PaymentResponse> paymentResponse = new MutableLiveData<>();
        apiService.getPaymentResponse("Bearer " + token, courseId).enqueue(new Callback<ResponseModel<PaymentResponse>>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseModel<PaymentResponse>> call, retrofit2.Response<ResponseModel<PaymentResponse>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getResult() != null){
                    PaymentResponse payment = response.body().getResult().getData();
                    paymentResponse.setValue(payment);
                }else{
                    paymentResponse.setValue(null);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseModel<PaymentResponse>> call, Throwable t) {
                Log.e("ORDER_ERROR", "API Call Failed: " + t.getMessage());
                paymentResponse.setValue(null);
            }
        });
        return paymentResponse;
    }

}
