package com.adapter.rest;

import java.util.Map;
import java.util.Objects;

public class Request {
    private final String method;
    private final String path;
    private final String params;
    private final String host;
    private final String body;
    private final String contentType;


    public Request(Map<String, String> request) {
        this.method = request.get("method");
        this.path = request.get("path");
        this.params = Objects.nonNull(request.get("params")) ? request.get("params") : "";
        // TODO: temporario
        this.host = "http://localhost:8080";
        this.body = request.get("body");
        this.contentType = request.get("contentType");
    }

    public Request(String method, String path, String params, String host, String body, String contentType) {
        this.method = method;
        this.path = path;
        this.params = params;
        this.body = body;
        this.host = host;
        this.contentType = contentType;
    }

    public String getPath() {
        return path;
    }

    public String getParams() {
        return params;
    }

    public String getBody() {
        return body;
    }

    public String getMethod() {
        return method;
    }

    @Override
    public String toString() {
        if (!body.isEmpty() && params.isEmpty()) {
            return method + " " + path + " " + "HTTP/1.1\r\n" + host + "\r\n\n" + body;
        } else if (!body.isEmpty()) {
            return method + " " + path + " " + "/" + params + " " + "HTTP/1.1\r\n" + host + "\r\n\n" + body;
        } else {
            return method + " " + path + " " + "/" + "HTTP/1.1\r\n";
        }
    }

    public String getContentType() {
        return this.contentType;
    }
}
