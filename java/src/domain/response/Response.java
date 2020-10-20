package src.domain.response;

import src.domain.HttpStatus;

import java.io.File;

public abstract class Response {
    public abstract String makeResponse(File requestedFile);

    public abstract String makeHeaders();

    public String makeBaseHeaders(HttpStatus httpStatus) {
        return "HTTP/1.1 " + httpStatus.getStatusCode() + " " + httpStatus.getMessage() + " " + "\r\n" +
                "Server:  FACOM-CD-2020/1.0 \r\n";
//                "Content-Type: text/html \r\n\n";
    }
}
