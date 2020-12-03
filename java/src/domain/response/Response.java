package src.domain.response;

import src.domain.HttpStatus;

public class Response {
    private HttpResponseDTO httpResponseDTO;

    public Response(HttpResponseDTO httpResponseDTO) {
        this.httpResponseDTO = httpResponseDTO;
    }

    protected String makeBaseHeaders(HttpStatus httpStatus, int contentLength) {
        return String.format("HTTP/1.1 " + httpStatus.getStatusCode() + " " + httpStatus.getMessage() + " " + "\r\n" +
                "Server:  FACOM-CD-2020/1.0 \r\n" +
                "Content-Length: %d\r\n", contentLength);
    }

    @Override
    public String toString() {
        return "";
    }
}
