package com.bfh.ticket;

public class TicketMatch {
    private final long ticketPointer;
    private final double score;

    private TicketMatch(long ticketPointer, double score) {
        this.ticketPointer = ticketPointer;
        this.score = score;
    }

    public Ticket getTicket() {
        return getTicket(ticketPointer);
    }

    public double getScore() {
        return score;
    }

    private native Ticket getTicket(long ticketPointer);
}
