package com.huydang.fishingsalebackend.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserIsExistedException extends RuntimeException{
    private int code;
    private String message;
}
