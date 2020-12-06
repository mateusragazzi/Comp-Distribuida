package com.domain.exception;

import com.domain.HttpStatus;

public class MethodNotAllowedException extends HttpException {
    public MethodNotAllowedException() {
        super(HttpStatus.METHOD_NOT_ALLOWED);
    }
}
