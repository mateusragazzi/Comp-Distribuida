package src;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Http {
    public static final String URL = "url";
    public static final String METHOD = "method";
    public static final String PROTOCOL_VERSION = "protocolVersion";
    private final int WAITING = 0;
    private final int CONNECTED = 1;
    private final String SERVER_PATH = System.getProperty("user.dir");

    private int currentState = WAITING;

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
    public String processInput(String request) {
        Map<String, String> requestData;
        String response = "";

        switch (currentState) {
            case WAITING:
                currentState = CONNECTED;
                break;
            case CONNECTED:
                requestData = parseRequest(request);

                handleRequest(requestData.get(URL));

                response = "HTTP/1.1 200 Document follows \n" +
                        "Server:  FACOM-CD-2020/1.0 \n" +
                        "Content-Type: text/plain \n" +
                        "Bye.";
                currentState = WAITING;
                break;
        }

        return response;
    }

    private void handleRequest(String url) {
        url = SERVER_PATH + url;
        File myDir = new File(url);
        if (myDir.isDirectory()) {
            File[] contents = myDir.listFiles();
            Arrays.stream(contents)
                    .forEach(file ->
                            System.out.println(file.getName() + ": " + file.length()));
        }
        // else if is a file else 404
    }

    private Map<String, String> parseRequest(String request) {
        Map<String, String> requestData = new HashMap<>();

        String[] inputElements = request.split("%n");
        String[] requestFirstLine = inputElements[0].split(" ");

        requestData.put(METHOD, requestFirstLine[0]);
        requestData.put(URL, requestFirstLine[1]);
        requestData.put(PROTOCOL_VERSION, requestFirstLine[2]);

        return requestData;
    }
}
