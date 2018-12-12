package com.example.makkhay.mewpractice;

import com.example.makkhay.mewpractice.model.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitClient {

  @GET("/api/cats")
  Call<List<Model>> getApiDetail(@Query("page") int page);
}
