package com.huydang.fishingsalebackend.config;

import com.huydang.fishingsalebackend.exception.InvalidRegisterCode;
import com.huydang.fishingsalebackend.exception.UserIsExistedException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({UserIsExistedException.class,
            BadCredentialsException.class,
            HttpMessageNotReadableException.class,
            InvalidRegisterCode.class,
            ExpiredJwtException.class
    })
    public ResponseEntity<String> handlerAuthException(RuntimeException e) {
        return ResponseEntity.status(405).body(e.getMessage());
    }
    @ExceptionHandler(BindException.class)
    public ResponseEntity<String> handleBindException(BindException e) {
        return ResponseEntity.badRequest().body(e
                .getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage());
    }
    @ExceptionHandler
    public ResponseEntity<String> handlerUserException(RuntimeException e) {
        return ResponseEntity.status(300).body(e.getMessage());
    }
}
