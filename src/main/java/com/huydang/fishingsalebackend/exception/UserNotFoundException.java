package com.huydang.fishingsalebackend.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserNotFoundException extends RuntimeException{
    private int code;
    private String message;
}
