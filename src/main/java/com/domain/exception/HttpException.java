package com.domain.exception;

import com.domain.HttpStatus;

public class HttpException extends Exception {
    private final HttpStatus httpStatus;

    public HttpException(HttpStatus httpStatus) {
        super(httpStatus.getMessage());
        this.httpStatus = httpStatus;
    }

    public int statusCode() {
        return httpStatus.getStatusCode();
    }
}
