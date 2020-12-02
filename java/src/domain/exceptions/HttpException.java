package src.domain.exceptions;

import src.domain.HttpStatus;

public class HttpException extends Exception {
    private final HttpStatus httpStatus;

    public HttpException(HttpStatus httpStatus) {
        super(httpStatus.getMessage());
        this.httpStatus = httpStatus;
    }

    public String statusCode() {
        return httpStatus.getStatusCode();
    }
}
