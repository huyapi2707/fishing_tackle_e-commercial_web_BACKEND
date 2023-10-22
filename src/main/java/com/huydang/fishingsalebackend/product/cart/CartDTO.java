package com.huydang.fishingsalebackend.product.cart;


import lombok.Builder;

@Builder
public record CartDTO(
        String id,
        String product_name,
        Long product_id,
        String type_name,
        Long type_id,
        String image,
        Long quantity,
        Long amount
                      ) {
}
