package src;

import src.adapter.rest.HttpResponseDTO;
import src.adapter.rest.Request;
import src.adapter.rest.Response;
import src.domain.exception.HttpException;
import src.domain.exception.MethodNotAllowedException;

import java.util.HashMap;
import java.util.Map;

public class Http {
    public static final String PATH = "path";
    public static final String METHOD = "method";
    private static final String BODY = "body";

    private final Router router = new Router();

    public Response processRequest(String rawRequest) {
        Map<String, String> requestData;
        try {
            requestData = parseRequest(rawRequest);
            Request request = new Request(requestData);
            return router.route(request);
        } catch (HttpException httpException) {
            HttpResponseDTO dto = new HttpResponseDTO();
            dto.statusCode = httpException.statusCode();
            return new Response(dto);
        }
    }

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
