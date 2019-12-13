# Ticket recognition for java

## Getting started
The first example starts with the creation of a text model.
A model consists of n texts, a template name and the path to an image.
A text itself consists of a bounding-box and a key.
The bounding-box is described by two points.
These two points spread a rectangle.
```
BoundingBox boundingBoxOfTextKey = new BoundingBox(new Point(0, 0), new Point(110, 120));
Text text = new Text("text_key", boundingBoxOfTextKey);
List<Text> texts = Collections.singletonList(text);
Ticket ticket = new Ticket("template_name", new TicketImage("/path/to/template/template.jpg"), texts);
```

This ticket model can be used to train the matcher.
```
Matcher matcher = new Matcher(Algorithm.SIFT);
matcher.train(ticket);
```
Any previously trained template can be removed on runtime.
```
matcher.untrain(ticket);
```

Then, the matcher is prepared for usage. Any image can be passed.
```
TicketImage image = new TicketImage("path/to/image/image.jpg");
Optional<TicketMatch> matchOptional = matcher.match(image);
```

If the optional is present (any template has matched the input) the text value can be read
by a MetadataReader.
```
try (MetadataReader reader = new MetadataReader(Algorithm.SIFT)) {
    if (matchOptional.isPresent()) {
        Metadata metadata = reader.read(matchOptional.get().getTicket(), image);

        // Access extracted texts
        metadata.getTexts()
                .forEach((key, value) -> System.out.println("Key: " + key + "  Value: " + value));
    }
}
``` 

After usage, a ticket can be deleted and the matcher can be closed.
```
ticket.delete();
matcher.close();
```

*Full exampled*
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
