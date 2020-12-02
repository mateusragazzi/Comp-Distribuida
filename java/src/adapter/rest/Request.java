package src.adapter.rest;

public class Request {
    private final String method;
    private final String path;
    private final String params;
    private final String host;
    private final String body;

    public Request(String method, String path, String params, String host, String body) {
        this.method = method;
        this.path = path;
        this.params = params;
        this.body = body;
        this.host = host;
    }

    public Request(String method, String path, String params, String host) {
        this.method = method;
        this.path = path;
        this.params = params;
        this.host = host;
        body = "";
    }

    public Request(String method, String path, String host) {
        this.method = method;
        this.path = path;
        this.host = host;
        params = "";
        body = "";
    }

    public String getMethod() {
        return method;
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
}
