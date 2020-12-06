package com;

import com.adapter.rest.Request;
import com.adapter.rest.Response;
import com.domain.exception.HttpException;
import com.domain.exception.MethodNotAllowedException;

import java.lang.reflect.Array;
import java.util.*;

public class Http {
    public static final String PATH = "path";
    public static final String METHOD = "method";
    private static final String BODY = "body";
    private static final String CONTENT_TYPE = "contentType";

    private final Router router = new Router();

    public Response processRequest(String rawRequest, String rawBody) {
        Map<String, String> requestData;
        try {
            requestData = parseRequest(rawRequest, rawBody);
            Request request = new Request(requestData);
            return router.route(request);
        } catch (HttpException httpException) {
            return new Response(401, "application/json", httpException.getMessage());
        }
    }

    private Map<String, String> parseRequest(String request, String body) throws MethodNotAllowedException {
        Map<String, String> requestData = new HashMap<>();

        String[] requestTokens = request.split("\\r\\n");
        String[] requestFirstLine = requestTokens[0].split(" ");
        List<String> lastTokens = new ArrayList<>(Arrays.asList(requestTokens));
        lastTokens.remove(0);
        lastTokens.stream().forEach(httpLine -> {
            String[] lineTokens = httpLine.split(":");
            requestData.put(lineTokens[0], lineTokens[1].trim());
        });

        final String method = requestFirstLine[0];
        final String params = Optional.of(requestFirstLine[1].split("/\\w+/")[1]).orElse("");
        final String path = requestFirstLine[1].split("/\\d")[0];

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

    private boolean needsBody(String method) {
        return method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT") || method.equalsIgnoreCase("PATCH");
    }
}
