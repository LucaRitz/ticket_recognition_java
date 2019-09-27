package com.bfh.ticket;

import java.util.List;
import java.util.Optional;

public class Matcher {

    private final long pointer;

    public Matcher(long pointer) {
        this.pointer = pointer;
    }

    public void train(Ticket ticket) {
        train(pointer, ticket);
    }

    public void train(List<Ticket> tickets) {
        train(pointer, tickets);
    }

    public Optional<TicketMatch> match(TicketImage image) {
        return match(pointer, image);
    }

    public void delete() {
        delete(pointer);
    }

    private native void train(long pointer, Ticket ticket);
    private native void train(long pointer, List<Ticket> tickets);
    private native Optional<TicketMatch> match(long pointer, TicketImage image);
    private native void delete(long pointer);
}
