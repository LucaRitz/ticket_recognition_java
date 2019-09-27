package com.bfh.ticket;

import java.util.List;

public class Ticket {
    private final String name;
    private final TicketImage image;
    private final List<Text> texts;
    private final List<BoundingBox> matchingAreas;

    public Ticket(String name, TicketImage image, List<Text> texts, List<BoundingBox> matchingAreas) {
       this.name = name;
       this.image = image;
       this.texts = texts;
       this.matchingAreas = matchingAreas;
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

    public List<BoundingBox> getMatchingAreas() {
        return matchingAreas;
    }
}
