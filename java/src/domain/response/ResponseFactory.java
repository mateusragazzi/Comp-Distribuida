package src.domain.response;

import java.io.File;
import java.net.URL;

public class ResponseFactory {

    private static String callStack = "";
    private static final String FILES_PATH = System.getProperty("user.dir");

    /**
     * Função que determina qual tipo de resposta será devolvida ao cliente.
     * Ela invoca a classe responsável por responder aquela requisição.
     * Todas elas estendem a classe Response, unificando assim o tipo de resposta.
     *
     * @param filePath indica o caminho solicitado pelo cliente.
     * @param baseUrl indica a URL base do servidor
     * @return texto de resposta contendo o header e o body.
     */
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
                fileName.endsWith(".css") ;
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
}
