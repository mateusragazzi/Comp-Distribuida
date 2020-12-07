package com;

import com.adapter.rest.Request;
import com.adapter.rest.Response;
import com.domain.exception.HttpException;
import com.domain.exception.MethodNotAllowedException;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Http {
    public static final String PATH = "path";
    public static final String METHOD = "method";
    private static final String BODY = "body";
    private static final String CONTENT_TYPE = "contentType";

    private final Router router = new Router();

    public Response processRequest(String rawRequest, String rawBody) {
        Map<String, Object> requestData;
        try {
            requestData = parseRequest(rawRequest, rawBody);
            Request request = new Request(requestData);
            return router.route(request);
        } catch (HttpException httpException) {
            return new Response(401, "application/json", httpException.getMessage());
        }
    }

    /**
     * Recebe uma request em String e cria um mapa de suas informações
     **/
    private Map<String, Object> parseRequest(String request, String body) throws MethodNotAllowedException {
        Map<String, Object> requestData = new HashMap<>();

        String[] requestTokens = request.split("\\r\\n");
        String[] requestFirstLine = requestTokens[0].split(" ");
        List<String> lastTokens = new ArrayList<>(Arrays.asList(requestTokens));
        lastTokens.remove(0);
        lastTokens.forEach(httpLine -> {
            String[] lineTokens = httpLine.split(":");
            requestData.put(lineTokens[0], lineTokens[1].trim());
        });

        final String method = requestFirstLine[0];
        final List<String> urlTokens = new ArrayList<>(Arrays.asList(requestFirstLine[1].split("/")));
        final String path = urlTokens.get(1);
        List<String> params = urlTokens.size() < 2 ? Collections.EMPTY_LIST : new ArrayList<>(urlTokens.subList(2, urlTokens.size()));
        requestData.put(PATH, path);
        requestData.put("params", params);
        requestData.put(METHOD, method);
        requestData.put(BODY, body);

        if (method.equalsIgnoreCase("GET") ||
                method.equalsIgnoreCase("DELETE") ||
                method.equalsIgnoreCase("POST") ||
                method.equalsIgnoreCase("PUT") ||
                method.equalsIgnoreCase("PATCH")) {
            return requestData;
        } else {
            throw new MethodNotAllowedException();
        }
    }
}
