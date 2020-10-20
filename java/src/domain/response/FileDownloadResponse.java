package src.domain.response;

import src.domain.HttpStatus;

import java.io.File;

// TODO: arrumar resposta: enviar bytes do documento no corpo da resposta
public class FileDownloadResponse extends Response {
    public FileDownloadResponse(File requestedFile) {
        super(requestedFile);
    }

    @Override
    protected String makeResponseBody() {
        return processFile(requestedFile);
    }

    @Override
    protected String makeHeaders() {
        String fileName = requestedFile.getName();

        return makeBaseHeaders(HttpStatus.OK) +
                "Content-Type: application/octet-stream\r\n" +
                String.format("\r\nContent-Disposition: attachment; filename=%s", fileName) +
                "\r\n";
    }

    @Override
    public String buildResponse() {
        return makeHeaders() + makeResponseBody();
    }

    private String processFile(File requestedFile) {
        return requestedFile.getAbsolutePath();
    }
}
