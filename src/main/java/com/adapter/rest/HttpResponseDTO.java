package com.adapter.rest;

public class HttpResponseDTO {
    public int statusCode;
    public String contentType;
    public String body;

    public HttpResponseDTO(int statusCode, String contentType, String body) {
        this.statusCode = statusCode;
        this.contentType = contentType;
        this.body = body;
    }
}
