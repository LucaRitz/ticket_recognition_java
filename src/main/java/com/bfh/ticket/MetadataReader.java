package com.bfh.ticket;

public class MetadataReader {
    private final long pointer;

    public MetadataReader(long pointer) {
        this.pointer = pointer;
    }

    public Metadata read(Ticket ticket, TicketImage image) {
        return read(pointer, ticket, image);
    }

    public void delete() {
        delete(pointer);
    }

    private native Metadata read(long pointer, Ticket ticket, TicketImage image);
    private native void delete(long pointer);
}
