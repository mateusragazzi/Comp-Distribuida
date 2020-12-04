import src.adapter.rest.Request;

public class HttpRequestBuilder {
    private String method;
    private String path;
    private String params;
    private String body;
    private String host;

    public HttpRequestBuilder() {
        method = "GET";
        path = "movies";
        params = "1";
        body = "";
        host = "http://localhost:8080";
    }

    public HttpRequestBuilder withMethod(String method) {
        this.method = method;
        return this;
    }

    public HttpRequestBuilder withBody(String body) {
        this.body = body;
        return this;
    }

    public HttpRequestBuilder withPath(String path) {
        this.path = path;
        return this;
    }

    public Request build() {
        return new Request(method, path, params, host, body);
    }
}
