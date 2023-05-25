package com.oceanapplication.ocean.services;


import com.oceanapplication.ocean.repo.UserRepository;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

    private final UserRepository userRepository;

    public String getCardNumber(String phoneNumber) {
        var user = userRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new UsernameNotFoundException("user not found"));
        if (user.getCardId() == null) {
            user.setCardId(generateNewCardId());
            userRepository.save(user);
        }
        return user.getCardId();
    }


    /**
     * Test version of generating barcode EAN13.
     * @return random generated barcode EAN13
     */
    public String generateNewCardId() {
        Call<AccessToken> call = ApiFactory.getService().getToken(CARDCODE);
    }
}
