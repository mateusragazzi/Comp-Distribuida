package src.domain.response;

import src.domain.HttpStatus;

import java.io.File;

public class NotFoundResponse extends Response{
    public NotFoundResponse(File requestedFile) {
        super(requestedFile);
    }

    @Override
    protected String makeResponseBody() {
        return "";
    }

    @Override
    protected String makeHeaders() {
        return makeBaseHeaders(HttpStatus.NOT_FOUND);
    }

    @Override
    public String buildResponse() {
        return makeHeaders();
    }
}
