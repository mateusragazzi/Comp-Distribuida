package src.domain.response;

import java.io.File;

public class NotFoundResponse extends Response{
    public NotFoundResponse(File requestedFile) {
        super(requestedFile);
    }

    @Override
    protected String makeResponseBody() {
        return null;
    }

    @Override
    protected String makeHeaders() {
        return null;
    }

    @Override
    public String buildResponse() {
        return null;
    }
}
