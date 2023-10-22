package com.huydang.fishingsalebackend.product.cart;

import com.huydang.fishingsalebackend.exception.UserNotFoundException;
import com.huydang.fishingsalebackend.product.type.TypeRepository;
import com.huydang.fishingsalebackend.user.User;
import com.huydang.fishingsalebackend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final UserRepository userRepository;
    private final TypeRepository typeRepository;

    public List<CartDTO> getCartsByUserId(Long id) {
        var optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
          return user.getCarts().stream().filter(cart -> !cart.getPaid()).map(cartMapper).collect(Collectors.toList());

        }
        return null;
    }

    public List<CartDTO> updateUserCart(CartServiceDTO payload) {
        var optionalUser = userRepository.findById(payload.user_id());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
             payload.carts().stream().forEach(cartDTO -> {
                var optionalType = typeRepository.findById(cartDTO.type_id());
                if (optionalType.isPresent()) {
                    if (cartDTO.id().equals("")) {
                        Cart newCart = Cart.builder()
                                .user(user)
                                .type(optionalType.get())
                                .quantity(cartDTO.quantity())
                                .paid(Boolean.FALSE)
                                .build();

                        cartRepository.save(newCart);
                        user.getCarts().add(newCart);
                    }
                    else {
                        for( Cart cart : user.getCarts() ){
                            if (cart.getId().equals(cartDTO.id())) {
                                cart.setQuantity(cartDTO.quantity());
                                cartRepository.save(cart);
                                break;
                            }
                        }
                    }
                }
                else throw new RuntimeException();

            });
             return user.getCarts().stream().map(cartMapper).collect(Collectors.toList());
        }
        throw new RuntimeException();
    }

    public String deleteCart(String id) {
        var cart = cartRepository.findById(id);
        if (cart.isPresent()) {
            cartRepository.delete(cart.get());
            return "Success";
        }
        return "False";
    }
}
