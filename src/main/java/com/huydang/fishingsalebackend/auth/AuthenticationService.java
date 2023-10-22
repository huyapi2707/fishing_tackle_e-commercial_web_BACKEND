package com.huydang.fishingsalebackend.auth;

import com.huydang.fishingsalebackend.exception.InvalidRegisterCode;
import com.huydang.fishingsalebackend.exception.UserIsExistedException;
import com.huydang.fishingsalebackend.jwt.JwtService;
import com.huydang.fishingsalebackend.jwt.JwtToken;
import com.huydang.fishingsalebackend.jwt.TokenRepository;
import com.huydang.fishingsalebackend.mail.EmailSenderService;
import com.huydang.fishingsalebackend.registerCode.RegisterCode;
import com.huydang.fishingsalebackend.registerCode.RegisterCodeRepository;
import com.huydang.fishingsalebackend.user.User;
import com.huydang.fishingsalebackend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final RegisterCodeRepository codeRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager manager;
    private  final EmailSenderService mailSender;

    private void saveUserToken(User user, String token) {
        var newToken = JwtToken.builder()
                .token(token)
                .referenceUser(user)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(newToken);
    }

    private void revokeAllUserToken(User user) {
        var storedTokens = tokenRepository.findAllByReferenceUserId(user.getId());
        storedTokens.forEach(
                token -> {
                    token.setExpired(true);
                    token.setRevoked(true);
                }
        );
    }

    private void saveUserVerifyCode(User user, String code) {
        var newCode = RegisterCode.builder()
                .refUser(user)
                .code(code)
                .expiredAt(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
                        .build();
        codeRepository.save(newCode);
    }

    private boolean isVerifyCodeValid(String code) {
        var registerCode = codeRepository.findByCode(code);
        if (registerCode.isPresent()) {
            return !(registerCode.get().getExpiredAt().before(new Date()));
        }
        return false;
    }

    public String register(RegisterRequest request) {
        var existedEmail = userRepository.findByEmail(request.getEmail());
        if (existedEmail.isPresent()) throw new UserIsExistedException(405, "User is existed");
        String code =String.format("PII-%03d", (int) (Math.random() * 1000 + 1));
        var newUser = User.builder()
                .username(null)
                .password(passwordEncoder.encode(request.getPassword()))
                .fullname(String.format("user"))
                .email(request.getEmail())
                .dob(null)
                .address(null)
                .role(request.getRole())
                .enable(false)
                .build();
        mailSender.sendEmailToUser(newUser, "Xác thực tạo tài khoản Pii", code);
        userRepository.save(newUser);
        saveUserVerifyCode(newUser,code);
        return "You need to active your account";
    }

    public AuthenticationResponse verifyAccount(String code) {
        if (!isVerifyCodeValid(code)) throw new InvalidRegisterCode(405, "Invalid register code");
        var user = userRepository.findByRegisterCode(code);
        if (!user.isPresent())  throw new InvalidRegisterCode(405, "Invalid register code");
        String token = jwtService.generateToken(user.get());
        user.get().setEnable(true);
        userRepository.save(user.get());
        saveUserToken(user.get(), token);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail()).get();
        if (user.isEnable() == false) throw  new BadCredentialsException("User account is not enabled");
        String token = jwtService.generateToken(user);
        revokeAllUserToken(user);
        saveUserToken(user, token);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }


}
