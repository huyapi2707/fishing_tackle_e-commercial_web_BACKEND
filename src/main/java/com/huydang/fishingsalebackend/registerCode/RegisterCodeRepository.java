package com.huydang.fishingsalebackend.registerCode;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterCodeRepository extends JpaRepository<RegisterCode, Long> {

    Optional<RegisterCode> findByCode(String code);
}
