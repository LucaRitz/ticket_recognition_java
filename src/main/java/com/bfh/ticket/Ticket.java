package com.bfh.ticket;

import java.util.List;

public class Ticket {
    private long pointer;
    private final String name;
    private final TicketImage image;
    private final List<Text> texts;

    public Ticket(String name, TicketImage image, List<Text> texts) {
       this.name = name;
       this.image = image;
       this.texts = texts;
    }

    public String getName() {
        return name;
    }

    public TicketImage getImage() {
        return image;
    }

    public List<Text> getTexts() {
        return texts;
    }

    // TODO: may remove public
    public void setPointer(long pointer) {
        this.pointer = pointer;
    }

    public void delete() {
        delete(pointer);
    }

    public native void delete(long pointer);
}
