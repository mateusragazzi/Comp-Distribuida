package src.domain.response;

import java.io.File;
import java.net.URL;

public class ResponseFactory {

    private static String callStack = "";
    private static final String FILES_PATH = System.getProperty("user.dir");

    public static Response create(String filePath, URL baseUrl)  {
        File requestedFile = new File(getFilePath(filePath));

        if (requestedFile.isDirectory()) {
            return new HtmlResponse(requestedFile, baseUrl, callStack);
        }
        if(requestedFile.isFile()) {
            return new FileDownloadResponse(requestedFile);
        }
        return new NotFoundResponse(requestedFile);
    }

    private static String getFilePath(String filePath) {
        if (!filePath.isEmpty() && !filePath.equals("/favicon.icon") && !filePath.equals("/")) {
            callStack = filePath.replaceFirst("/", "") + '/';
        }
        filePath = FILES_PATH + filePath;
        return filePath;
    }
}
