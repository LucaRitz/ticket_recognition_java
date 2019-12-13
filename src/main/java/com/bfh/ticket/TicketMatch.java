package com.bfh.ticket;

/**
 * Match returned by a MatchingAlgorithm or a Matcher indicating that a TicketImage
 * has been found in a set of Ticket's. The quality of the TicketMatch is indicated in it's score.
 */
public class TicketMatch {
    private final long ticketPointer;

    private TicketMatch(long ticketPointer) {
        this.ticketPointer = ticketPointer;
    }

    /**
     * Reference to the matched Ticket.
     * @return
     */
    public Ticket getTicket() {
        return getTicket(ticketPointer);
    }

    private native Ticket getTicket(long ticketPointer);
}
