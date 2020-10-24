package src.domain.response;

import java.io.File;

public class CgiBin extends Response{
    public CgiBin(File requestedFile) {
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
