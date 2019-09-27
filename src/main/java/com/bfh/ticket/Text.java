package com.bfh.ticket;

public class Text {
    private final String key;
    private final BoundingBox boundingBox;

    public Text(String key, BoundingBox boundingBox) {
        this.key = key;
        this.boundingBox = boundingBox;
    }

    public String getKey() {
        return key;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }
}
