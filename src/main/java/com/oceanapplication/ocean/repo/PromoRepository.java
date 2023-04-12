package com.oceanapplication.ocean.repo;

import com.oceanapplication.ocean.models.Promo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoRepository extends JpaRepository<Promo, Long> {
    Optional<Promo> findByHead(String head);
    Page<Promo> findByActive(Boolean active, Pageable pageable);
}
