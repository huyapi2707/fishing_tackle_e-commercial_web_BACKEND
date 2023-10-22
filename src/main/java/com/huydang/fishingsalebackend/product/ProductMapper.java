package com.huydang.fishingsalebackend.product;

import com.huydang.fishingsalebackend.product.brand.BrandMapper;
import com.huydang.fishingsalebackend.product.categories.CategoryMapper;
import com.huydang.fishingsalebackend.product.subCategory.SubCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ProductMapper implements Function<Product, ProductDTO> {
    private final CategoryMapper categoryMapper;
    private final SubCategoryMapper subCategoryMapper;
    private  final BrandMapper brandMapper;

    @Override
    public ProductDTO apply(Product product) {

            return new ProductDTO(
                    categoryMapper.apply(product.getSubtype().getCategory()),
                    subCategoryMapper.apply(product.getSubtype()),
                    product.getId(),
                    product.getName(),
                    brandMapper.apply(product.getBrand()),
                    product.getRating(),
                    product.getDescriptions(),
                    product.getImageSrcs(),
                    product.getTypes()
            );

    }
}
