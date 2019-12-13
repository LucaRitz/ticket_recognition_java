package com.bfh.ticket;

import com.bfh.ticket.exception.CtiException;

import java.util.List;
import java.util.Optional;

public class Matcher {

    private final long pointer;

    public Matcher(Algorithm algorithm) {
        this(algorithm, new MatcherOptions());
    }


    public Matcher(Algorithm algorithm, MatcherOptions options) {
        this.pointer = initialize(algorithm, options);
    }

    public void train(Ticket ticket) throws CtiException {
        train(pointer, ticket);
    }

    public void train(List<Ticket> tickets) throws CtiException {
        train(pointer, tickets);
    }

    public void untrain(Ticket ticket) {
        untrain(pointer, ticket.getPointer());
    }

    public Optional<TicketMatch> match(TicketImage image) {
        return match(pointer, image);
    }

    public void delete() {
        delete(pointer);
    }

    private native long initialize(Algorithm algorithm, MatcherOptions options);
    private native void train(long pointer, Ticket ticket) throws CtiException;
    private native void train(long pointer, List<Ticket> tickets) throws CtiException;
    private native void untrain(long pointer, long ticketPointer);
    private native Optional<TicketMatch> match(long pointer, TicketImage image);
    private native void delete(long pointer);
}
