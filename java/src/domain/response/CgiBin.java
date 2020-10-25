package src.domain.response;

import src.domain.HttpStatus;

import java.io.*;
import java.util.Arrays;
import java.util.Map;

public class CgiBin extends Response {
    private static final int SLUG = 0;
    private static final int FILE = 1;
    private final String filePath;

    public CgiBin(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Monta a resposta para o servidor, concatenando o header com o body
     * Utilizada em Http.java, caso a Factory tenha o invocado.
     * @return resposta formada.
     */
    @Override
    public String buildResponse() {
        return readProcessOutput();
    }

    /**
     * Monta o body da requisição, que é a saída do Script.
     * @return saída do programa executado.
     */
    @Override
    protected String makeResponseBody() { return ""; }

    /**
     * Monta o header para servidor.
     * Entretanto, o CGI-BIN tem o header especificado dentro script a ser executado.
     * @return String vazia.
     */
    @Override
    protected String makeHeaders() {
        return "";
    }

    private String readProcessOutput() {
        String filePath = getRequestedFilePath();
        StringBuilder procOutput = new StringBuilder();
        String command = System.getProperty("user.dir") +
                "/java/resources/" +
                filePath;
        try {
            ProcessBuilder pb = new ProcessBuilder(command);
            createEnvVariables(pb);
            Process proc = pb.start();
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String lineRead;
            while ((lineRead = br.readLine()) != null)
                procOutput
                        .append(lineRead)
                        .append("\r\n");
            proc.destroy();
        } catch (IOException | ArrayIndexOutOfBoundsException exception) {
            exception.printStackTrace();
            return makeBaseHeaders(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getMessage().length());
        }
        String content = procOutput.toString();
        return makeBaseHeaders(HttpStatus.OK, content.length()) +
                "Content-Type: text/html\r\n\n" +
                content;
    }

    private String getRequestedFilePath() {
        String[] filePathTokens = parseQueryParamsFromSlug(this.filePath)[SLUG].split("/");
        return filePathTokens[filePathTokens.length - 1];
    }

    private String[] parseQueryParamsFromSlug(String url) {
        return url.split("\\?");
    }

    /**
     * Função responsável por criar as variáveis de ambiente passadas por Query String.
     * @param pb - contém o ambiente que o código será executado.
     */
    private void createEnvVariables(ProcessBuilder pb) {
        Map<String, String> env = pb.environment();
        String queryParams = parseQueryParamsFromSlug(filePath)[FILE];
        String[] params = queryParams.split("&");
        Arrays.stream(params)
                .forEach(param -> {
                    String[] paramKeyValue = param.split("=");
                    env.put(paramKeyValue[0].toUpperCase(), paramKeyValue[1]);
                });
    }
}
