package com.bfh.ticket;

import java.util.Map;

/**
 * Metadata found on a ticket to be processed. This is the result of a MetadataReader.
 */
public class Metadata {

    private final Map<String, String> texts;

    /**
     * Construct a Metadata object from a map of texts found on a ticket.
     * @param texts Map of texts found on a ticket. The key is the Text's key property, the value is the text recognized by OCR.
     */
    private Metadata(Map<String, String> texts) {
        this.texts = texts;
    }

    /**
     * Map of texts found on a ticket. The key is the Text's key property, the value is the text recognized by OCR.
     * @return
     */
    public Map<String, String> getTexts() {
        return texts;
    }
}
