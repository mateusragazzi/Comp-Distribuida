package src.domain.response;

import src.exception.NotFoundException;

import java.io.File;
import java.net.URI;

public class ResponseFactory {
    public static Response create(URI fileUri) throws NotFoundException {
        File requestedFile = new File(fileUri);
        if (!requestedFile.exists()) {
            throw new NotFoundException();
        }
        if (requestedFile.isDirectory())
            return new HtmlResponse();

        return new FileDownloadResponse();
    }
}
