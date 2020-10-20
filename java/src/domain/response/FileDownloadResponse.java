package src.domain.response;

import java.io.File;
import java.net.URL;

public class FileDownloadResponse extends Response {
    public FileDownloadResponse() {
    }

    @Override
    public String makeResponse(File requestedFile) {
        return processFile(requestedFile);
    }

    @Override
    public String makeHeaders() {
        return null;
    }

    private String processFile(File requestedFile) {
        return requestedFile.getAbsolutePath();
    }
}
