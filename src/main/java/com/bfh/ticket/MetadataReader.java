package com.bfh.ticket;

import com.bfh.ticket.exception.CtiException;

public class MetadataReader {
    private final long pointer;

    public MetadataReader(Algorithm algorithm) {
        this(algorithm, new MetadataReaderOptions());
    }

    public MetadataReader(Algorithm algorithm, MetadataReaderOptions options) {
        this.pointer = initialize(algorithm, options);
    }

    public Metadata read(Ticket ticket, TicketImage image) throws CtiException {
        return read(pointer, ticket.getPointer(), image);
    }

    public void delete() {
        delete(pointer);
    }

    private native long initialize(Algorithm algorithm, MetadataReaderOptions options);
    private native Metadata read(long pointer, long ticketPointer, TicketImage image) throws CtiException;
    private native void delete(long pointer);
}
