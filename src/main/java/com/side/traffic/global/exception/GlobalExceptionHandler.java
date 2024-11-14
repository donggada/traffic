package com.side.traffic.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.side.traffic.global.exception.ErrorCode.*;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({ ApiException.class })
    protected ResponseEntity handleApiException(ApiException ex) {
        return new ResponseEntity(ErrorDto.of(ex.getErrorCode()), HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler({ UsernameNotFoundException.class })
    protected ResponseEntity UsernameNotFoundException(ApiException ex) {
        return new ResponseEntity(ErrorDto.of(UNAUTHORIZED_ACCESS), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ Exception.class })
    protected ResponseEntity exception(Exception ex) {
        log.error("Error message = {}", ex.getMessage(), ex);
        return new ResponseEntity(ErrorDto.of(INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
