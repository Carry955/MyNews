package com.example.mynews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecycleAdapter adapter;
    private Context context;
    private ArrayList<News.Data> list;
    private News news;
    private int page= 1;
    private int page_size = 10;
    GetRequest request;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
        request();
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if(page == 50){
                        Toast.makeText(getApplicationContext(), "没有更多了", Toast.LENGTH_SHORT);
                        return;
                    }
                    page++;
                    Call<News> call = request.getCall(page, page_size);
                    call.enqueue(new Callback<News>() {
                        @Override
                        public void onResponse(Call<News> call, Response<News> response) {
                            Log.d("mtest", response.body().getReason());
                            news = response.body();
                            adapter.addNews(news.getResult().getData());
                        }

                        @Override
                        public void onFailure(Call<News> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }
    public void request(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://v.juhe.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        request = retrofit.create(GetRequest.class);
        Call<News> call = request.getCall(page, page_size);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                Log.d("mtest", response.body().getReason());
                news = response.body();
                adapter = new RecycleAdapter(context, news.getResult().getData());
                list = news.getResult().getData();
                LinearLayoutManager manager = new LinearLayoutManager(context);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }
}