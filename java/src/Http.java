package src;

import helpers.HttpStatus;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// TODO: implementar threads
public class Http {
    public static final String URL = "url";
    public static final String METHOD = "method";
    public static final String PROTOCOL_VERSION = "protocolVersion";
    public static final String STATUS = "STATUS";
    private static final String PROTOCOL = "http://";
    private static final String HOST = "localhost:";
    private final String FILES_PATH = System.getProperty("user.dir");
    private final URL baseUrl;

    public Http(int portNumber) throws MalformedURLException {
        this.baseUrl = new URL(PROTOCOL + HOST + portNumber + "/");
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

    // TODO: nao trata mensagens continuas do cliente
    public String processRequest(String request) {
        Map<String, String> requestData;
        String response = "";
        requestData = parseRequest(request);
        String responseBody = makeResponseBody(requestData.get(URL));
        response = makeResponse(responseBody, requestData.get(STATUS));

        return response;
    }

    // TODO: melhorar formacao da resposta
    private String makeResponse(String responseBody, String statusCode) {
        StringBuilder response = new StringBuilder();

        response.append("HTTP/1.1 ");
        response.append(statusCode);
        response.append(" Document follows \n");
        response.append("Server:  FACOM-CD-2020/1.0 \r\n");
        response.append("Content-Type: text/html \r\n\n");
        response.append(responseBody);

        return response.toString();
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

    private String listDirectoriesAsHtml(File[] contents) {
        StringBuilder html = new StringBuilder();
        html.append("<html>");
        html.append("<body>");
        html.append("<ul>");
        Arrays.stream(contents)
                .forEach(file -> {
                    html.append("<li>")
                            .append(buildAnchorTag(file))
                            .append("</li>");
                });
        html.append("</ul>");
        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }

    /**
     * @param file
     * @return
     * @todo Implementar anchor com diretório corrente
     */
    private String buildAnchorTag(File file) {
        return String.format("<a href='%s'>%s</a>", baseUrl + file.getName(), file.getName());
    }

    private Map<String, String> parseRequest(String request) {
        Map<String, String> requestData = new HashMap<>();

        String[] inputElements = request.split("\n");
        String[] requestFirstLine = inputElements[0].split(" ");

        try {
            if (requestFirstLine[0].equalsIgnoreCase("GET")) {
                requestData.put(METHOD, requestFirstLine[0]);
                requestData.put(URL, requestFirstLine[1]);
                requestData.put(PROTOCOL_VERSION, requestFirstLine[2]);
                requestData.put(STATUS, HttpStatus.OK.getStatusCode());
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            requestData.put(STATUS, HttpStatus.BAD_GATEWAY.getStatusCode());
            return requestData;
        }

        return requestData;
    }
}
