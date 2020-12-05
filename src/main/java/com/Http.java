package com;

import com.adapter.rest.Request;
import com.adapter.rest.Response;
import com.domain.exception.HttpException;
import com.domain.exception.MethodNotAllowedException;

import java.util.HashMap;
import java.util.Map;

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

        String[] requestTokens = request.split("\n");
        String[] requestFirstLine = requestTokens[0].split(" ");
        final String method = requestFirstLine[0];

        if (needsBody(method)) {
            requestData.put(METHOD, method);
            requestData.put(PATH, requestFirstLine[1]);
            requestData.put(BODY, body);
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
