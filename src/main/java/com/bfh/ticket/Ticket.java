package com.bfh.ticket;

import java.util.List;

/**
 * Template of a ticket to be processed. It describes how one kind of ticket may look, using a TicketImage,
 * and which Text's are to be found in which BoundingBoxes of the TicketImage.
 * It is used to train a MatchingAlgorithm or a Matcher before matching a TicketImage of a ticket to be processed.
 * It is used to read the Texts from a TicketImage of a ticket to be processed using
 * a ExtractionAlgorithm or a MetadataReader.
 * It must be uniquely identifiable among a set of Ticket's using a name.
 */
public class Ticket implements AutoCloseable {
    private long pointer;
    private final String name;
    private final TicketImage image;
    private final List<Text> texts;

    /**
     * Constructs a Ticket.
     * @param name Identification of a Ticket. Must be unique among a set of Tickets.
     * @param image Image of the Ticket used as a reference in the matching phase.
     * @param texts Set of Texts that can be found in a TicketImage if it is an image of this Ticket.
     * @throws CtiException if any of the provided Texts' BoundingBox lies outside of the given TicketImage's bounds.
     */
    public Ticket(String name, TicketImage image, List<Text> texts) {
       this.name = name;
       this.image = image;
       this.texts = texts;
    }

    /**
     * Identification of a Ticket. Must be unique among a set of Tickets.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Image of the Ticket used as a reference in the matching phase.
     * @return
     */
    public TicketImage getImage() {
        return image;
    }

    /**
     * Set of Texts that can be found in a TicketImage if it is an image of this Ticket.
     * @return
     */
    public List<Text> getTexts() {
        return texts;
    }

    /**
     * Delete this object.
     */
    public void delete() {
        delete(pointer);
    }

    /**
     * Delete this object.
     */
    @Override
    public void close() {
        this.delete();
    }

    long getPointer() {
        return pointer;
    }

    private native void delete(long pointer);
}
