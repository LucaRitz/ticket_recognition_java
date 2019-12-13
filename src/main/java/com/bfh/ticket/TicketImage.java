package com.bfh.ticket;

/**
 * Image of a concrete ticket to be processed or the template image of a Ticket.
 */
public class TicketImage {

    private final String imagePath;

    /**
     * Construct a TicketImage from a filepath pointing to an image file.
     * @param imagePath
     */
    public TicketImage(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }
}
