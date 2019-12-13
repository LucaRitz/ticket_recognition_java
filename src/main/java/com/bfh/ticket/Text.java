package com.bfh.ticket;

/**
 * Definition of a text that is to be found on a Ticket inside a specified BoundingBox.
 */
public class Text {

    private final String key;
    private final BoundingBox boundingBox;

    /**
     * Construct a Text.
     * @param key Identification of the Text. Must be unique for a single Ticket.
     * @param boundingBox BoundingBox where the Text is to be found on a Ticket.
     */
    public Text(String key, BoundingBox boundingBox) {
        this.key = key;
        this.boundingBox = boundingBox;
    }

    /**
     * Identification of the Text. Must be unique for a single Ticket.
     * @return
     */
    public String getKey() {
        return key;
    }

    /**
     * BoundingBox where the Text is to be found on a Ticket.
     * @return
     */
    public BoundingBox getBoundingBox() {
        return boundingBox;
    }
}
