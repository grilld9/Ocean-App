package com.oceanapplication.ocean.repo;

import com.oceanapplication.ocean.models.Promo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoRepository extends JpaRepository<Promo, Long> {
    Optional<Promo> findByHead(String head);
}
