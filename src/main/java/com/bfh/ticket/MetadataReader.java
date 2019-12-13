package com.bfh.ticket;

import com.bfh.ticket.exception.CtiException;

/**
 * Provides the ability to read all Metadata located on a ticket to be processed, provided as a TicketImage,
 * and a Ticket that describes the kind of ticket and what kind of Metadata can be found.
 * It makes sense to use this in combination with a Matcher, after matching a Ticket using a TicketImage, the
 * Metadata can be read out using this class.
 */
public class MetadataReader implements AutoCloseable {

    private final long pointer;

    /**
     * Constructs a MetadataReader using a given implementation of a Algorithm with default options.
     * @param algorithm Algorithm used for Metadata extraction.
     */
    public MetadataReader(Algorithm algorithm) {
        this(algorithm, new MetadataReaderOptions());
    }

    /**
     * Constructs a MetadataReader using a given implementation of a Algorithm with given options.
     * @param algorithm Algorithm used for Metadata extraction.
     * @param options Options.
     */
    public MetadataReader(Algorithm algorithm, MetadataReaderOptions options) {
        this.pointer = initialize(algorithm, options);
    }

    /**
     * Read as much Metadata as possible located on the given TicketImage, defined in the given Ticket.
     * This uses the ExtractionAlgorithm provided at construction of this class.
     * @return a pointer to a dynamically allocated instance of Metadata.
     * @throws CtiException if the Metadata cannot be read from the given TicketImage.
     */
    public Metadata read(Ticket ticket, TicketImage image) throws CtiException {
        return read(pointer, ticket.getPointer(), image);
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

    private native long initialize(Algorithm algorithm, MetadataReaderOptions options);
    private native Metadata read(long pointer, long ticketPointer, TicketImage image) throws CtiException;
    private native void delete(long pointer);
}
