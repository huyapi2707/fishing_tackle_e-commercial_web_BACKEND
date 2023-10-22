package com.huydang.fishingsalebackend.product.description;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DescriptionRepository extends JpaRepository<Description, Long> {
}
