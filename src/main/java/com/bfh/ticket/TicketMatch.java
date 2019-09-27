package com.bfh.ticket;

public class TicketMatch {
    private final long pointer;

    public TicketMatch(long pointer) {
        this.pointer = pointer;
    }

    public String getName() {
        return getName(pointer);
    }

    public void delete() {
        delete(pointer);
    }

    private native String getName(long pointer);
    private native void delete(long pointer);
}
