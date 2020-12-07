package com;

import com.adapter.rest.Request;

import java.util.Arrays;

public class RequestBuilder {
    private String method;
    private String path;
    private String params;
    private String body;
    private String host;
    private String contentType;

    public RequestBuilder() {
        method = "GET";
        path = "movies";
        params = "1";
        body = "";
        contentType = "text/json";
        host = "http://localhost:8080";
    }

    public RequestBuilder withMethod(String method) {
        this.method = method;
        return this;
    }

    public RequestBuilder withBody(String body) {
        this.body = body;
        return this;
    }

    public RequestBuilder withPath(String path) {
        this.path = path;
        return this;
    }

    public RequestBuilder withContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public Request build() {
        return new Request(method, path, Arrays.asList(params), host, body, contentType);
    }
}
