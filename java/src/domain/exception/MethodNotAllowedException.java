package src.domain.exception;

import src.domain.HttpStatus;

public class MethodNotAllowedException extends HttpException {
    public MethodNotAllowedException() {
        super(HttpStatus.METHOD_NOT_ALLOWED);
    }
}
