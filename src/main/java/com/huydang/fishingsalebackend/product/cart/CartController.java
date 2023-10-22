package com.huydang.fishingsalebackend.product.cart;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService service;

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<List<CartDTO>> updateUserCart(@RequestBody CartServiceDTO payload) {
        return ResponseEntity.ok(service.updateUserCart(payload));
    }

    @RequestMapping(value = "/get")
    public ResponseEntity<List<CartDTO>> getUserCart(@RequestParam Long id) {
        return ResponseEntity.ok(service.getCartsByUserId(id));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<String> deleteCartById(@RequestBody String id) {
        return ResponseEntity.ok(service.deleteCart(id));
    }
}
