package src;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// TODO: implementar threads
public class Http {
    public static final String URL = "url";
    public static final String METHOD = "method";
    public static final String PROTOCOL_VERSION = "protocolVersion";
    private final String FILES_PATH = System.getProperty("user.dir");

    private final int WAITING = 0;
    private final int CONNECTED = 1;
    private static final int DISCONNECTED = 2;

    private int currentState = WAITING;
    private String base_url = "http://localhost:";

    public Http(int portNumber) {
        this.base_url = base_url.concat(String.valueOf(portNumber)).concat("/");
    }

    /*
       Request
        GET <sp> <Documento> <sp> HTTP/1.1 <crlf>
       {Outras informacoes de cabecalho}
       <crlf>
    */

    /*
    Response
    HTTP/1.1 <sp> 200 <sp> Document <sp> follows <crlf>
    Server: <sp> <Server-Type> <crlf>
    Content-type: <sp> <Document-Type> <crlf>
    {Outras informacoes de cabecalho}
    <crlf>
    <Dados do Documento>
    */
    int count = 0;

    // TODO: nao trata mensagens continuas do cliente
    public String processInput(String request) {
        Map<String, String> requestData;
        String response = "";
        if (currentState == WAITING) {
            currentState = CONNECTED;
            response = makeResponse("");
        } else if (currentState == CONNECTED) {
            requestData = parseRequest(request);
            String responseBody = makeResponseBody(requestData.get(URL));
            response = makeResponse(responseBody);
        } else {
            currentState = DISCONNECTED;
        }

        return response;
    }

    // TODO: melhorar formacao da resposta
    private String makeResponse(String responseBody) {
        String response;
        response = "HTTP/1.1 200 Document follows \r\n" +
                "Server:  FACOM-CD-2020/1.0 \r\n" +
                "Content-Type: text/html \r\n\n" +
                responseBody;
        return response;
    }

    private String makeResponseBody(String url) {
        url = FILES_PATH + url;
        File requestedFile = new File(url);
        return requestedFile.exists() ? processFile(requestedFile) : null;
    }

    //TODO: Criar forma para download de arquivo
    private String processFile(File requestedFile) {
        if (requestedFile.isDirectory()) {
            File[] contents = requestedFile.listFiles();
            return listDirectoriesAsHtml(contents);
        }
        return requestedFile.getAbsolutePath();
    }

    // TODO: Criar navegacao recursiva
    private String listDirectoriesAsHtml(File[] contents) {
        StringBuilder html = new StringBuilder();
        html.append("<html>");
        html.append("<body>");
        html.append("<ul>");
        Arrays.stream(contents)
                .forEach(file -> {
                    html.append("<li>")
                            .append(String.format("<a href='%s'>%s</a>", base_url + file.getName(), file.getName()))
                            .append("</li>");
                });
        html.append("</ul>");
        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }

    private Map<String, String> parseRequest(String request) {
        Map<String, String> requestData = new HashMap<>();

        String[] inputElements = request.split("\n");
        String[] requestFirstLine = inputElements[0].split(" ");
        requestData.put(METHOD, requestFirstLine[0]);
        requestData.put(URL, requestFirstLine[1]);
        requestData.put(PROTOCOL_VERSION, requestFirstLine[2]);

        return requestData;
    }
}
