package com.huydang.fishingsalebackend.product.subCategory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubCategoryService {
    private final SubCategoryRepository subtypeRepository;
    private final SubCategoryMapper subtypeMapper;

    public List<SubCategoryDTO> getAllName() {
        return subtypeRepository.findAll().stream()
                .map(subtypeMapper)
                .collect(Collectors.toList());
    }
}
