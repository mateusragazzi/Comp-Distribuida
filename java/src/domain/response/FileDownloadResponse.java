package src.domain.response;

import java.io.File;

public class FileDownloadResponse implements Response {
    @Override
    public String makeResponse() {
        File requestedFile = new File(makeFileUrl(url));
        return requestedFile.exists() ? processFile(requestedFile) : "File Not Found";
    }



}
