package src.domain.response;

import src.exception.NotFoundException;

import java.io.File;
import java.net.URI;
import java.net.URL;

public class ResponseFactory {
    public static Response create(URI fileUri, URL baseUrl) throws NotFoundException {
        File requestedFile = new File(fileUri);

        if (!requestedFile.exists()) {
            throw new NotFoundException();
        }
        if (requestedFile.isDirectory()) {
            return new HtmlResponse(baseUrl);
        }

        return new FileDownloadResponse();
    }
}
