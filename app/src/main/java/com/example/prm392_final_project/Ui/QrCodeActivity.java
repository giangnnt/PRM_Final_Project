package com.example.prm392_final_project.Ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;

import com.example.prm392_final_project.Model.PaymentResponse;
import com.example.prm392_final_project.R;
import com.example.prm392_final_project.Repository.OrderRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import java.util.UUID;

public class QrCodeActivity extends AppCompatActivity {

    private ImageView imageViewQr;
    private OrderRepository orderRepository = new OrderRepository();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_qr_code);

        // Check if user is logged in
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String token = preferences.getString("access_token", null);
        if (token == null) {
            startActivity(new Intent(this, AuthActivity.class));
            finish();
            return;
        }
        // Get the course ID from the intent
        String courseIdString = getIntent().getStringExtra("courseId");
        UUID courseId = UUID.fromString(courseIdString); // Chuyển từ chuỗi thành UUID

        LiveData<PaymentResponse> paymentResponse = orderRepository.GetPaymentResponse(token, courseId);

        // Khởi tạo ImageView
        imageViewQr = findViewById(R.id.imageViewQr);

        // Quan sát paymentResponse
        paymentResponse.observe(this, response -> {
            if (response != null) {
                // Giả sử getQrCode() trả về String
                String qrCodeContent = response.getQrCode();
                if (qrCodeContent != null) {
                    Bitmap qrBitmap = generateQrCode(qrCodeContent);
                    imageViewQr.setImageBitmap(qrBitmap);
                }
            }
        });

        Button btnBackToMenu = findViewById(R.id.btnBackToMenu);
        btnBackToMenu.setOnClickListener(v -> {
            Intent intent = new Intent(QrCodeActivity.this, MainActivity.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    // Hàm chuyển String thành Bitmap QR code
    private Bitmap generateQrCode(String content) {
        int width = 300; // Chiều rộng QR code
        int height = 300; // Chiều cao QR code
        try {
            MultiFormatWriter writer = new MultiFormatWriter();
            BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height);
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}