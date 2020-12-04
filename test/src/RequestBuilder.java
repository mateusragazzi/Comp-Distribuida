package src;

import src.adapter.rest.Request;

public class RequestBuilder {
    private String method;
    private String path;
    private String params;
    private String body;
    private String host;

    public RequestBuilder() {
        method = "GET";
        path = "movies";
        params = "1";
        body = "";
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

    public Request build() {
        return new Request(method, path, params, host, body);
    }
}