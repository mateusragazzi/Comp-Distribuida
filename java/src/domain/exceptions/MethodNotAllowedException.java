package src.domain.exceptions;

import src.domain.HttpStatus;

public class MethodNotAllowedException extends HttpException {
    public MethodNotAllowedException() {
        super(HttpStatus.METHOD_NOT_ALLOWED);
    }
}
