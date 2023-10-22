package com.huydang.fishingsalebackend.product.brand;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public List<BrandDTO> getAllName() {
        return brandRepository.findAll().stream()
                .map(brandMapper)
                .collect(Collectors.toList());
    }
}
