package com.huydang.fishingsalebackend.product.cart;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CartMapper implements Function<Cart, CartDTO> {
    @Override
    public CartDTO apply(Cart cart) {
        return CartDTO.builder()
                .id(cart.getId())
                .product_name(cart.getType().getProduct().getName())
                .product_id(cart.getType().getProduct().getId())
                .image(cart.getType().getProduct().getImageSrcs().get(0).getSrc())
                .type_name(cart.getType().getType())
                .type_id(cart.getType().getId())
                .quantity(cart.getQuantity())
                .amount(cart.getQuantity() * cart.getType().getPrice())
                .build();


    }
}
