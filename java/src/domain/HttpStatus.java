package src.domain;

public enum HttpStatus {
    OK("200", "Document follows"),
    NOT_FOUND("404", "Document Not Found"),
    BAD_REQUEST("400", "Bad Request");

    private final String statusCode;
    private final String message;

    HttpStatus(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
