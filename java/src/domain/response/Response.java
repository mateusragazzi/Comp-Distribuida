package src.domain.response;

import src.domain.HttpStatus;

import java.io.File;
import java.net.URL;

public abstract class Response {
    protected File requestedFile;

    public Response(File requestedFile){
        this.requestedFile = requestedFile;
    }
    protected abstract String makeResponseBody();

    protected abstract String makeHeaders();

    public abstract String buildResponse();

    protected String makeBaseHeaders(HttpStatus httpStatus) {
        return "HTTP/1.1 " + httpStatus.getStatusCode() + " " + httpStatus.getMessage() + " " + "\r\n" +
                "Server:  FACOM-CD-2020/1.0 \r\n";
    }
}
