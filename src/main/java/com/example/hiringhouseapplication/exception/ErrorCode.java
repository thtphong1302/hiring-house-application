package com.example.hiringhouseapplication.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    UNCATEGORIZED_CODE(999,"Uncategorized code",HttpStatus.INTERNAL_SERVER_ERROR),
    RESOURCES_NOT_EXIST(1001,"RESOURCES NOT EXISTS", HttpStatus.BAD_REQUEST),
    RESOURCES_ALREADY_EXISTS(1002,"RESOURCES ALREADY EXISTS", HttpStatus.BAD_REQUEST),
    INVALID_DAY(1003,"Invalid day", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code,String message,HttpStatus httpStatus){
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;
}
