package com.oceanapplication.ocean.factories;

import com.oceanapplication.ocean.services.ApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {
    private static final String ROOT_URL = "https://your_api_address";

    static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
            .baseUrl(ROOT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    public static ApiService getService() {
        return buildRetrofit().create(ApiService.class);
    }

}
