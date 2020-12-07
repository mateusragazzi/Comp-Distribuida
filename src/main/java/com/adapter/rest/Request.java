package com.adapter.rest;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Request {
    private final String method;
    private final String path;
    private final List<String> params;
    private final String host;
    private final String body;
    private final String contentType;


    public Request(Map<String, Object> request) {
        this.method = (String) request.get("method");
        this.path = (String) request.get("path");
        this.params = (List<String>) request.get("params");
        this.host = "http://localhost:8080";
        this.body = (String) request.get("body");
        this.contentType = (String) request.get("Content-Type");
    }

    public Request(String method, String path, List<String> params, String host, String body, String contentType) {
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

    public List<String> getParams() {
        return params;
    }

    public String getBody() {
        return body;
    }

    public String getMethod() {
        return method;
    }

    public String getContentType() {
        return this.contentType;
    }
}
