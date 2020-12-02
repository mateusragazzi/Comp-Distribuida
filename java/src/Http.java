package src;

import src.domain.exceptions.HttpException;
import src.domain.exceptions.MethodNotAllowedException;
import src.domain.response.ResponseFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Http {
    public static final String PATH = "url";
    public static final String METHOD = "method";
    private static final String PROTOCOL = "http://";
    private static final String HOST = "localhost:";
    private static final String BODY = "body";

    private final URL baseUrl;

    public Http(int portNumber) throws MalformedURLException {
        this.baseUrl = new URL(PROTOCOL + HOST + portNumber + "/");
    }

    /**
     * Função que processa uma requisição, invocando a Factory
     *
     * @param request
     * @return retorna ao servidor uma resposta à requisição feita.
     */
    public String processRequest(String request) {
        Map<String, String> requestData;
        try {
            requestData = parseRequest(request);

            return ResponseFactory.create(requestData.get(PATH), baseUrl).buildResponse();
        } catch (HttpException httpException) {
            return ResponseFactory.createError(httpException);
        }
    }

    /**
     * Extrai informações da requisição HTTP
     *
     * @param request request feita pelo cliente
     * @return map contendo os itens encontrados.
     */
    private Map<String, String> parseRequest(String request) throws MethodNotAllowedException {
        Map<String, String> requestData = new HashMap<>();

        String[] requestTokens = request.split("\n");
        String[] requestFirstLine = requestTokens[0].split(" ");
        final String method = requestFirstLine[0];

        if (needsBody(method)) {
            requestData.put(METHOD, method);
            requestData.put(PATH, requestFirstLine[1]);
            requestData.put(BODY, requestTokens[3]);
            return requestData;
        }
        if (method.equalsIgnoreCase("GET") || method.equalsIgnoreCase("DELETE")) {
            requestData.put(METHOD, method);
            requestData.put(PATH, requestFirstLine[1]);
            return requestData;
        } else {
            throw new MethodNotAllowedException();
        }
    }

    private boolean needsBody(String method) {
        return method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT") || method.equalsIgnoreCase("PATCH");
    }
}
