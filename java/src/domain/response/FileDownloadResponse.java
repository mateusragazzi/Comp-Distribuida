package src.domain.response;

import src.domain.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

// TODO: arrumar resposta: enviar bytes do documento no corpo da resposta
public class FileDownloadResponse extends Response {
    public FileDownloadResponse(File requestedFile) {
        super(requestedFile);
    }

    @Override
    protected String makeResponseBody() {
        return processFile(requestedFile) + "\r\n\n";
    }

    @Override
    protected String makeHeaders() {
        String fileName = requestedFile.getName();

        return makeBaseHeaders(HttpStatus.OK) +
                "Content-Type: application/octet-stream\r\n" +
                String.format("Content-Disposition: attachment; filename=%s", fileName) +
                "\r\n\n";
    }

    @Override
    public String buildResponse() {
        return makeHeaders() + makeResponseBody();
    }

    private String processFile(File requestedFile) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(requestedFile.getAbsolutePath()));
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException ie) {
            System.err.println(ie.getMessage());
            return "";
        }
    }
}
