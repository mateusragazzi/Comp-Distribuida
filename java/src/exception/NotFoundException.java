package src.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super("File Not Found");
    }
}
