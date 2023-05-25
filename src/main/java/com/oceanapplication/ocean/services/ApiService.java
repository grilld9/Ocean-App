package com.oceanapplication.ocean.services;

import com.oceanapplication.ocean.dto.CardDTO;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("card/generate")
    Call<CardDTO> getToken(
        @Query("cardCode") String cardCode);
}
