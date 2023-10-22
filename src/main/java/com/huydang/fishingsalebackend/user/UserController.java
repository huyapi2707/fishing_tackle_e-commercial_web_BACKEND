package com.huydang.fishingsalebackend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<UserDTO> getUser(@RequestParam String token) {
        return ResponseEntity.ok(userService.getUserByToken(token));
    }

}
