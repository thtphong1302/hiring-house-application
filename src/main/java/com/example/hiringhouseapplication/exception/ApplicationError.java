package com.example.hiringhouseapplication.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationError extends RuntimeException{

    private ErrorCode errorCode;

    public ApplicationError(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
