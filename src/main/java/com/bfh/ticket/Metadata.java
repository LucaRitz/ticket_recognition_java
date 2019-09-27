package com.bfh.ticket;

import java.util.Map;

public class Metadata {
    private final long pointer;

    public Metadata(long pointer) {
        this.pointer = pointer;
    }

    public Map<String, String> getTexts() {
        return getTexts(pointer);
    }

    public void delete() {
        delete(pointer);
    }

    private native Map<String, String> getTexts(long pointer);
    private native void delete(long pointer);
}
