package com.side.traffic.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDto  {
    private  int status;
    private String message;

    public static ErrorDto of (ErrorCode errorCode) {
        return new ErrorDto(errorCode.getStatus(), errorCode.getMessage());
    }
}
