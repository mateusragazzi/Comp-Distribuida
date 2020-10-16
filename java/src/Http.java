package src;

import src.domain.response.HtmlResponse;
import src.domain.response.Response;
import src.domain.response.ResponseFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Http {
    public static final String URL = "url";
    public static final String METHOD = "method";
    public static final String PROTOCOL_VERSION = "protocolVersion";
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

    public Response processRequest(String request) throws URISyntaxException {
        Map<String, String> requestData;
        requestData = parseRequest(request);
        String responseBody = makeResponseBody(requestData.get(URL));

        return ResponseFactory.create(makeFileUri(requestData.get(URL)));
    }

    // TODO: melhorar formacao da resposta
    private String makeHttpResponse(String responseBody) {
        String response;
        response = "HTTP/1.1 200 Document follows \r\n" +
                "Server:  FACOM-CD-2020/1.0 \r\n" +
                "Content-Type: text/html \r\n\n" +
                responseBody;
        return response;
    }

    //TODO: Criar forma para download de arquivo
    private String processFile(File requestedFile) {
        if (requestedFile.isDirectory()) {
            File[] contents = requestedFile.listFiles();
            return listDirectoriesAsHtml(contents);
        }
        System.out.println(requestedFile);
        return requestedFile.getAbsolutePath();
    }

    private String listDirectoriesAsHtml(File[] contents) {
        StringBuilder html = new StringBuilder();
        html.append("<html>");
        html.append("<body>");
        html.append("<ul>");
        Arrays.stream(contents)
                .forEach(file -> html.append("<li>")
                        .append(buildAnchorTag(file))
                        .append("</li>"));
        html.append("</ul>");
        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }

    private String buildAnchorTag(File file) {
        return String.format("<a href='%s'>%s</a>", baseUrl + file.getName(), file.getName());
    }

    private Map<String, String> parseRequest(String request) {
        Map<String, String> requestData = new HashMap<>();

        String[] inputElements = request.split("\n");
        String[] requestFirstLine = inputElements[0].split(" ");

        if (requestFirstLine[0].equalsIgnoreCase("GET")) {
            requestData.put(METHOD, requestFirstLine[0]);
            requestData.put(URL, requestFirstLine[1]);
            requestData.put(PROTOCOL_VERSION, requestFirstLine[2]);
        }

        return requestData;
    }

    private URI makeFileUri(String url) throws URISyntaxException {
        if(url.startsWith("/"))
            url = FILES_PATH + url;
        else
            url = FILES_PATH + "/" + url;
        return new URI(url);
    }
}
