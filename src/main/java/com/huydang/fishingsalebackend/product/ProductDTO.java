package com.huydang.fishingsalebackend.product;

import com.huydang.fishingsalebackend.product.brand.BrandDTO;
import com.huydang.fishingsalebackend.product.categories.CategoryDTO;
import com.huydang.fishingsalebackend.product.description.Description;
import com.huydang.fishingsalebackend.product.imageSrc.ImageSrc;
import com.huydang.fishingsalebackend.product.subCategory.SubCategoryDTO;
import com.huydang.fishingsalebackend.product.type.Type;

import java.util.ArrayList;
import java.util.List;

public record ProductDTO(
        CategoryDTO category,
        SubCategoryDTO subCategory,
        Long id,
        String name,
        BrandDTO brand,
        Long rating,
        List<Description> descriptions,
        List<ImageSrc> images,
        List<Type> types
) {
}
