package com.example.testgiuaky2;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testgiuaky2.config.PrefManager;
import com.example.testgiuaky2.config.RetrofitCilent;
import com.example.testgiuaky2.mapper.CategoryMapper;
import com.example.testgiuaky2.model.Category;
import com.example.testgiuaky2.model.CategoryResponse;
import com.example.testgiuaky2.service.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//phamdangkhoa_22110353
//Đào Thanh Tú - 22110452
//Nguyễn Thị Hồng Hà - 22110001


public class HomeFragment extends AppCompatActivity {
    RecyclerView rcCate;
    CategoryAdapter categoryAdapter;
    ApiService apiService;
    private TextView tvUserName;
    List<Category> categoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        AnhXa();
        LoadUserName(); // Added from version 2
        GetCategory();
    }

    private void AnhXa() {
        rcCate = findViewById(R.id.rc_category);
        rcCate.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        tvUserName = findViewById(R.id.tvUserName); // Added from version 2
    }

    // Added from version 2
    private void LoadUserName() {
        // Get user name from PrefManager
        PrefManager prefManager = new PrefManager(this);
        String userName = prefManager.getUserName();
        tvUserName.setText("Hi! " + userName);
    }

    private void GetCategory() {
        apiService = RetrofitCilent.getRetrofitInstance().create(ApiService.class);

        // Using the API endpoint from version 1 - keeping this exactly as in version 1
        apiService.getCategoryResponse().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CategoryResponse categoryResponse = response.body();

                    // Check if the API call was successful according to the response
                    if (categoryResponse.getCode() == 200 && categoryResponse.getResult() != null) {
                        // Use CategoryMapper to convert the results to Category objects
                        categoryList = CategoryMapper.toCategoryList(categoryResponse.getResult());

                        // Initialize adapter and set up RecyclerView
                        categoryAdapter = new CategoryAdapter(HomeFragment.this, categoryList);
                        rcCate.setHasFixedSize(true);
                        rcCate.setAdapter(categoryAdapter);
                        categoryAdapter.notifyDataSetChanged();
                    } else {
                        Log.d("API Error", "API returned code: " + categoryResponse.getCode() +
                                " with message: " + categoryResponse.getMessage());
                    }
                } else {
                    int statusCode = response.code();
                    Log.d("API Error", "Response not successful: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Log.d("API Error", "Call failed: " + t.getMessage());
            }
        });
    }
}