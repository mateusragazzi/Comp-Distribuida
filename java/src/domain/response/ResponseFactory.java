package src.domain.response;

import src.exception.NotFoundException;

import java.io.File;
import java.net.URL;

public class ResponseFactory {
    public static Response create(String filePath, URL baseUrl) throws NotFoundException {
        System.out.println(filePath);
        File requestedFile = new File(filePath);

        if (!requestedFile.exists()) {
            return new NotFoundResponse(requestedFile);
        }
        if (requestedFile.isDirectory()) {
            return new HtmlResponse(requestedFile, baseUrl);
        }

        return new FileDownloadResponse(requestedFile);
    }
}
