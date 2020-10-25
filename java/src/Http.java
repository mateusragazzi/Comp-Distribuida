package src;

import src.domain.response.ResponseFactory;

import java.net.MalformedURLException;
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

    private final URL baseUrl;

    public Http(int portNumber) throws MalformedURLException {
        this.baseUrl = new URL(PROTOCOL + HOST + portNumber + "/");
    }

    /**
     * Função que processa uma requisição, invocando a Factory
     * @param request
     * @return retorna ao servidor uma resposta à requisição feita.
     */
    public String processRequest(String request) {
        Map<String, String> requestData;
        requestData = parseRequest(request);
        return ResponseFactory
                .create(requestData.get(URL), baseUrl)
                .buildResponse();
    }

    /**
     * Realiza a leitura da request, para determinar o método, qual o protocolo e a url desejada
     * @param request request feita pelo cliente
     * @return map contendo os itens encontrados.
     */
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
}
