package src.domain.response;

import src.domain.exceptions.HttpException;

import java.io.File;
import java.net.URL;

// TODO: classes de resposta serão removidas. Esta classe vai se tornar um router

public class ResponseFactory {

    private static String callStack = "";
    private static final String FILES_PATH = System.getProperty("user.dir");

    public static Response create(String filePath, URL baseUrl) {
        File requestedFile = new File(getFilePath(filePath));

        if (filePath.contains("cgi-bin")) {
            return new CgiBin(filePath);
        }
        if (requestedFile.isDirectory()) {
            return new HtmlResponse(requestedFile, baseUrl, callStack);
        }
        if (requestedFile.isFile() && !isStatic(requestedFile)) {
            return new FileDownloadResponse(requestedFile);
        }
        return new NotFoundResponse();
    }

    private static boolean isStatic(File requestedFile) {
        String fileName = requestedFile.getName();
        return fileName.endsWith(".js") ||
                fileName.endsWith(".ts") ||
                fileName.endsWith(".htm") ||
                fileName.endsWith(".html") ||
                fileName.endsWith(".css");
    }

    /**
     * Função que retorna o caminho absoluto do arquivo a ser processado.
     * Importante para o CGI-BIN e atualização da callStack.
     *
     * @param filePath caminho atual
     * @return caminho absoluto do arquivo/pasta solicitado.
     */
    private static String getFilePath(String filePath) {
        if (!filePath.isEmpty() && !filePath.equals("/favicon.icon") && !filePath.equals("/")) {
            callStack = filePath.replaceFirst("/", "") + '/';
        }
        filePath = FILES_PATH + filePath;
        return filePath;
    }

    // TODO: Adicionar parser json
    public static String createError(HttpException exception) {
        return exception.getMessage();
    }
}
