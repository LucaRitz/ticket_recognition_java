package com.bfh.ticket;

public class TicketMatch {
    private final long ticketPointer;

    private TicketMatch(long ticketPointer) {
        this.ticketPointer = ticketPointer;
    }

    public Ticket getTicket() {
        return getTicket(ticketPointer);
    }

    private native Ticket getTicket(long ticketPointer);
}
