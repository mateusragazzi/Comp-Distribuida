package src;

import src.domain.response.Response;
import src.domain.response.ResponseFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Http {
    public static final String URL = "url";
    public static final String METHOD = "method";
    public static final String PROTOCOL_VERSION = "protocolVersion";
    private static final String PROTOCOL = "http://";
    private static final String HOST = "localhost:";
    private static final String FILES_PATH = System.getProperty("user.dir");

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
        return ResponseFactory.create(makeFileUri(requestData.get(URL)), baseUrl);
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
