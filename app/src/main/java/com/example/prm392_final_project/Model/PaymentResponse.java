package com.example.prm392_final_project.Model;

import com.google.gson.annotations.SerializedName;

public class PaymentResponse {
    @SerializedName("qrCode")
    private String qrCode;
    @SerializedName("paymentLink")
    private String paymentLink;

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getPaymentLink() {
        return paymentLink;
    }

    public void setPaymentLink(String paymentLink) {
        this.paymentLink = paymentLink;
    }
}
