package com.huydang.fishingsalebackend.user;

import java.time.LocalDate;

public record UserDTO(
         Long id,
         String username,
         String fullname,
         String phone,
         boolean gender,
         LocalDate dob,
         String email,
         String address,
         String role
) {
}
