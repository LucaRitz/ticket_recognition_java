package com.bfh.ticket;

import com.bfh.ticket.exception.CtiException;

import java.util.List;
import java.util.Optional;

/**
 * Provides the ability to train a set of Ticket's that describe all supported kinds of tickets.
 * After training, a ticket to be processed can be matched against the trained set of Ticket's using a TicketImage.
 * The image of the ticket is compared to all known Ticket's and if it matches,
 * the Ticket with the best score is returned.
 * It may also be that no Ticket matches because the TicketImage shows a kind of ticket that is unknown.
 * It makes sense to use this in combination with a MetadataReader, which can be used after matching for
 * extracting Metadata that is located on the given TicketImage and which is defined in the matched Ticket.
 */
public class Matcher implements AutoCloseable {

    private final long pointer;

    /**
     * Constructs a Matcher using a given Algorithm and default options.
     * @param algorithm Algorithm used for Ticket matching.
     */
    public Matcher(Algorithm algorithm) {
        this(algorithm, new MatcherOptions());
    }

    /**
     * Constructs a Matcher using a given Algorithm and default options.
     * @param algorithm Algorithm used for Ticket matching.
     * @param options Options.
     */
    public Matcher(Algorithm algorithm, MatcherOptions options) {
        this.pointer = initialize(algorithm, options);
    }

    /**
     * Train a single Ticket so that a TicketImage can be matched against it.
     * Note that this operation may take some time. The Ticket is preprocessed in order to save time when matching.
     * If the Ticket has a name that has already been trained, previous training data is
     * discarded and it is trained again.
     * If multiple Ticket are to be trained, a different overload of this function should be used,
     * since training multiple Ticket's is more efficient than training every single one of them independently.
     * @param ticket Ticket to be trained.
     */
    public void train(Ticket ticket) throws CtiException {
        train(pointer, ticket);
    }

    /**
     * Train a set of Ticket so that a TicketImage can be matched against them.
     * Note that this operation may take some time. The Tickets are preprocessed in order to save time when matching.
     * If one of the Tickets has a name that has already been trained, previous training data is
     * discarded and that Ticket is trained again.
     * @param tickets Tickets to be trained.
     */
    public void train(List<Ticket> tickets) throws CtiException {
        train(pointer, tickets);
    }

    /**
     * Discard all training data associated to the given Ticket's name.
     * The Ticket will no longer be matched against on subsequent calls to match.
     * @param ticket Ticket to be untrained.
     */
    public void untrain(Ticket ticket) {
        untrain(pointer, ticket.getPointer());
    }

    /**
     * Match a ticket to be processed, provided as a TicketImage, against the previouly trained set of Tickets.
     * @return an optional TicketMatch describing the one Ticket that matched or an empty optional if none matched.
     */
    public Optional<TicketMatch> match(TicketImage image) {
        return match(pointer, image);
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

    private native long initialize(Algorithm algorithm, MatcherOptions options);
    private native void train(long pointer, Ticket ticket) throws CtiException;
    private native void train(long pointer, List<Ticket> tickets) throws CtiException;
    private native void untrain(long pointer, long ticketPointer);
    private native Optional<TicketMatch> match(long pointer, TicketImage image);
    private native void delete(long pointer);
}
