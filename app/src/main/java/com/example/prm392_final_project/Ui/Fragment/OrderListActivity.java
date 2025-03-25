package com.example.prm392_final_project.Ui.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prm392_final_project.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderListActivity extends AppCompatActivity {

    private ListView orderListView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> orderList;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yoruorderlist);

        orderListView = findViewById(R.id.orderListView);
        orderList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orderList);
        orderListView.setAdapter(adapter);

        fetchOrders();
    }

    private void fetchOrders() {
        executorService.execute(() -> {
            String response = getOrdersFromApi();
            runOnUiThread(() -> processApiResponse(response));
        });
    }

    private String getOrdersFromApi() {
        StringBuilder result = new StringBuilder();
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("access_token", "");
        try {
            URL url = new URL("https://devkid.online/api/orders");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setRequestProperty("accept", "*/*");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
        } catch (Exception e) {
            Log.e("OrderListActivity", "Error fetching orders", e);
        }
        return result.toString();
    }

    private void processApiResponse(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject order = jsonArray.getJSONObject(i);
                String orderInfo = "ID: " + order.getInt("id") + " - " + order.getString("name");
                orderList.add(orderInfo);
            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            Toast.makeText(OrderListActivity.this, "Lỗi khi phân tích dữ liệu!", Toast.LENGTH_SHORT).show();
            Log.e("OrderListActivity", "JSON parsing error", e);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}
