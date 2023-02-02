package com.app.a2401962453_uasmobileprogramming.network;

import com.app.a2401962453_uasmobileprogramming.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApiInterface {
    @GET("/3/movie/{category}")
    Call<Response> getMovie(
            @Path("category") String category,
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page,
            @Query("region") String region
    );
}
