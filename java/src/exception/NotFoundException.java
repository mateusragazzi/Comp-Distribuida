package src.exception;

import src.domain.HttpStatus;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super(HttpStatus.NOT_FOUND.getMessage());
    }
}
