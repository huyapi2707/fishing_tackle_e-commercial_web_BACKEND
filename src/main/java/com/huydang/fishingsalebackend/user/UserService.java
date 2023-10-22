package com.huydang.fishingsalebackend.user;

import com.huydang.fishingsalebackend.exception.UserNotFoundException;
import com.huydang.fishingsalebackend.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    private final JwtService jwtService;

    public UserDTO getUserByToken(String token) {
        String email = jwtService.extractEmail(token);
        User user  = userRepository.findByEmail(email).get();
        return userDTOMapper.apply(user);
    }

}
