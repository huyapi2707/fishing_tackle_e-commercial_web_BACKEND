package com.huydang.fishingsalebackend.product.cart;

import lombok.Builder;

import java.util.List;

@Builder
public record CartServiceDTO(
        Long user_id,
        List<CartDTO> carts
) {
}
