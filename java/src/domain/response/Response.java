package src.domain.response;

import src.domain.HttpStatus;

public abstract class Response {
    protected abstract String makeResponseBody();

    protected abstract String makeHeaders();

    public abstract String buildResponse();

    protected String makeBaseHeaders(HttpStatus httpStatus, int contentLength) {
        return String.format("HTTP/1.1 " + httpStatus.getStatusCode() + " " + httpStatus.getMessage() + " " + "\r\n" +
                "Server:  FACOM-CD-2020/1.0 \r\n" +
                "Content-Length: %d\r\n", contentLength);
    }
}
