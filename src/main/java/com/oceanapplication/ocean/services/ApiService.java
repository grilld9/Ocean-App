package com.oceanapplication.ocean.services;

import com.oceanapplication.ocean.dto.CardDTO;
import retrofit2.Call;
import retrofit2.http.POST;

public interface ApiService {
    @POST("card/generate")
    Call<CardDTO> getCardNumber();
}
