# Ticket recognition for java

## Usage
```
// Create ticket template model
BoundingBox boundingBoxOfTextKey = new BoundingBox(new Point(0, 0), new Point(110, 120));
Text text = new Text("text_key", boundingBoxOfTextKey);
List<Text> texts = new ArrayList<>();
texts.add(text);
Ticket ticket = new Ticket("template_name", new TicketImage("/path/to/template/template.jpg"), texts);

// Create matcher and train
Matcher matcher = new Matcher(Algorithm.SIFT);
matcher.train(ticket);

// Match image
TicketImage image = new TicketImage("path/to/image/image.jpg");
Optional<TicketMatch> matchOptional = matcher.match(image);

// Read information if matching was successfully
try (MetadataReader reader = new MetadataReader(Algorithm.SIFT)) {
    if (matchOptional.isPresent()) {
        Metadata metadata = reader.read(matchOptional.get().getTicket(), image);

        // Access extracted texts
        metadata.getTexts()
                .forEach((key, value) -> System.out.println("Key: " + key + "  Value: " + value));
    }
} finally {
    // Untrain ticket template
    matcher.untrain(ticket);

    // Finally delete templates close matcher
    ticket.delete();
    matcher.close();
}
```
