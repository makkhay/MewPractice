package com.example.makkhay.mewpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.makkhay.mewpractice.adapter.RecyclerViewAdapter;
import com.example.makkhay.mewpractice.model.Model;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    LinearLayoutManager layoutManager;

  private static final int PAGE_START = 0;
  private boolean isLoading = false;
  private boolean isLastPage = false;
  private int TOTAL_PAGES = 3; //your total page
  private int currentPage = PAGE_START;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);

        loadApi(PAGE_START);
        mRecyclerView.addOnScrollListener(new PaginationScrollListener(layoutManager) {
          @Override
          protected void loadMoreItems() {
              isLoading = true;
              currentPage += 1;
              loadApi(PAGE_START);
            Toast.makeText(MainActivity.this,"loading more", Toast.LENGTH_SHORT).show();

          }

          @Override
          public int getTotalPageCount() {
            return TOTAL_PAGES;

          }

          @Override
          public boolean isLastPage() {
            Toast.makeText(MainActivity.this,"isLastPage", Toast.LENGTH_SHORT).show();


            return isLastPage;
          }

          @Override
          public boolean isLoading() {
            return isLoading;
          }
      });
    }

    public void loadApi(int page){
        Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://chex-triplebyte.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        RetrofitClient client = retrofit.create(RetrofitClient.class);
        Call<List<Model>> call = client.getApiDetail(page);

        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {

                List<Model> info = response.body();
                mRecyclerView.setAdapter( new RecyclerViewAdapter(MainActivity.this,info));
                mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));

                Toast.makeText(MainActivity.this,"success", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {

                Toast.makeText(MainActivity.this,"Error", Toast.LENGTH_SHORT).show();

            }
        });

    }



}
