package com.example.mynews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetRequest {
    @GET("toutiao/index?type=top&key=8a7982f79107d1e2e68fdf3b6d980579")
    Call<News> getCall(@Query("page") int page, @Query("page_size") int page_size);
}
