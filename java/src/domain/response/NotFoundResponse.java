package src.domain.response;

import src.domain.HttpStatus;

public class NotFoundResponse extends Response {

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
