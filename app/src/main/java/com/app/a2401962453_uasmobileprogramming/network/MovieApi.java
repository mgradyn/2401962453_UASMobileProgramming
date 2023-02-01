package com.app.a2401962453_uasmobileprogramming.network;

import com.app.a2401962453_uasmobileprogramming.config.NetworkConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieApi {
    private static final String BASE_URL = NetworkConfig.API_ENDPOINT;

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
        }
        return retrofit;
    }

}
