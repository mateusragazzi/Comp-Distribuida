package src;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Http {
    private final int WAITING = 0;
    private final int CONNECTED = 1;

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
                response = "HTTP/1.1 200 Document follows \n" +
                        "Server:  FACOM-CD-2020/1.0 \n" +
                        "Content-Type: text/plain \n" +
                        "Bye. \n";
                currentState = WAITING;
                break;
        }

        return response;
    }

    private Map<String, String> parseRequest(String request) {
        Map<String, String> requestData = new HashMap<>();

        String[] inputElements = request.split("%n");
        String[] requestFirstLine = inputElements[0].split(" ");

        requestData.put("method", requestFirstLine[0]);
        requestData.put("document", requestFirstLine[1]);
        requestData.put("httpVersion", requestFirstLine[2]);

        return requestData;
    }
}
