package com.adapter.rest;

import com.ContentType;
import com.adapter.rest.serializer.JsonSerializer;
import com.adapter.rest.serializer.XmlSerializer;
import com.domain.HttpStatus;

import java.util.Objects;

public class Response {
    private final int statusCode;
    private final String contentType;
    private final Object body;

    public Response(int statusCode, String contentType, Object body) {
        this.statusCode = statusCode;
        this.contentType = contentType;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getContentType() {
        return contentType;
    }

    public String getBody() {
        if (ContentType.JSON.getType().equals(this.contentType) || Objects.isNull(this.contentType))
            return new JsonSerializer().serialize(body);
        else if (ContentType.XML.getType().equals(this.contentType))
            return new XmlSerializer().serialize(body);
        return "Bad request";
    }

    @Override
    public String toString() {
        return makeBaseHeaders() + "\r\n" + getBody() + "\r\n";
    }

    protected String makeBaseHeaders() {
        return String.format("HTTP/1.1 " + "200" + " " + HttpStatus.OK.getMessage() + " " + "\r\n" +
                "Server:  FACOM-CD-2020/1.0 \r\n" +
                "Content-Type: %s\r\n" +
                "Content-Length: %d\r\n", getContentType(), getBody().getBytes().length);
    }
}
