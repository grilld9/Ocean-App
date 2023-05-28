package com.oceanapplication.ocean.services;


import com.oceanapplication.ocean.dto.CardDTO;
import com.oceanapplication.ocean.repo.UserRepository;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

@Service
@RequiredArgsConstructor
public class CardService {

    private final UserRepository userRepository;

    private final ApiService apiService;
    public String getCardNumber(String phoneNumber) {
        var user = userRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new UsernameNotFoundException("user not found"));
        if (user.getCardId() == null) {
            var cardId = generateNewCardId().orElseThrow(
                    () -> new NoSuchElementException("Cant generate new card"));
            user.setCardId(cardId.getCardCode());
            userRepository.save(user);
        }
        return user.getCardId();
    }



    public Optional<CardDTO> generateNewCardId() {
        Call<CardDTO> call = apiService.getCardNumber();
        try {
            Response<CardDTO> response = call.execute();
            if (response.isSuccessful()) {
                return Optional.ofNullable(response.body());
            } else {
                return Optional.empty();
            }
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
