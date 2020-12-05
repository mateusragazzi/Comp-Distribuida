package com;

public enum ContentType {
    JSON("application/json"), XML("application/xml");

    private final String type;

    ContentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
