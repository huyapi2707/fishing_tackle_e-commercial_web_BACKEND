package com.huydang.fishingsalebackend.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotEmpty(message = "Email field is empty")
    @Email
    private String email;

    @NotEmpty(message = "Password field is empty")
    @Size(min = 5, max = 10, message = "Password must be more than 7 character")
    private String password;

    @NotEmpty(message = "Role field is empty")
    private String role;
}
