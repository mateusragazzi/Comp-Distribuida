package src.domain.response;

import src.domain.HttpStatus;

public class NotFoundResponse extends Response {

    @Override
    protected String makeResponseBody() {
        return "";
    }

    @Override
    protected String makeHeaders() {
        return makeBaseHeaders(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getMessage().length());
    }

    @Override
    public String buildResponse() {
        return makeHeaders();
    }
}
