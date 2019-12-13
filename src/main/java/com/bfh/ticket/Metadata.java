package com.bfh.ticket;

import java.util.Map;

public class Metadata {

    private final Map<String, String> texts;

    private Metadata(Map<String, String> texts) {
        this.texts = texts;
    }

    public Map<String, String> getTexts() {
        return texts;
    }
}
