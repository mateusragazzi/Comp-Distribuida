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

    @Override
    protected String makeResponseBody() {
        return "";
    }

    @Override
    protected String makeHeaders() {
        return "";
    }

    @Override
    public String buildResponse() {
        return readProcessOutput();
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
            Arrays.stream(exception.getStackTrace()).forEach(e -> System.err.println(e.toString()));
            return makeBaseHeaders(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getMessage().length());
        }
        String content =  procOutput.toString();
        return makeBaseHeaders(HttpStatus.OK, content.length()) + content;
    }

    private String getRequestedFilePath() {
        String[] filePathTokens = parseQueryParamsFromSlug(this.filePath)[SLUG].split("/");
        return filePathTokens[filePathTokens.length - 1];
    }

    private String[] parseQueryParamsFromSlug(String url) {
        return url.split("\\?");
    }

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
