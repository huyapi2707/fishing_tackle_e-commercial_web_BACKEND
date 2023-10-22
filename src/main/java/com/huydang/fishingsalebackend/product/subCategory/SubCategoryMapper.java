package com.huydang.fishingsalebackend.product.subCategory;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SubCategoryMapper implements Function<SubCategory, SubCategoryDTO> {
    @Override
    public SubCategoryDTO apply(SubCategory subtype) {
        return new SubCategoryDTO(
                subtype.getId(),
                subtype.getName()
        );
    }
}
