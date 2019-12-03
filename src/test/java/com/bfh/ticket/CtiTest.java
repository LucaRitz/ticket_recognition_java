package com.bfh.ticket;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CtiTest {

    private static final Logger LOG = Logger.getLogger(CtiTest.class.getName());

    private Cti cti;

    @BeforeAll
    static void init() {
        try {
            System.loadLibrary("ticket_recognition_jcpp");
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @BeforeEach
    void setUp() {
        cti = new Cti();
    }

    @Test
    void cti_createMatcherAndDelete_matcherCreated() throws IOException {
        // Act
        Matcher matcher = cti.matcher(Algorithms.SIFT.name());

        Path file = extract(CtiTest.class.getResourceAsStream("/Ticket.jpg"));
        List<Text> texts = new ArrayList<>();
        texts.add(new Text("name", new BoundingBox(new Point(66,50), new Point(249,87))));
        Ticket ticket = new Ticket("template_1", new TicketImage(file.toAbsolutePath().normalize().toString()), texts);
        matcher.train(ticket);

        Optional<TicketMatch> match = matcher.match(new TicketImage(file.toAbsolutePath().normalize().toString()));
        LOG.info("Is present: " + match.isPresent());
        if (match.isPresent()) {
            LOG.info("Matched name: " + match.get().getTicket().getName());

            MetadataReader reader = cti.reader(Algorithms.SIFT.name());
            Metadata data = reader.read(match.get().getTicket(), new TicketImage(file.toAbsolutePath().normalize().toString()));
            Map<String, String> extracted = data.getTexts();
            for(Map.Entry<String, String> entry : extracted.entrySet()) {
                LOG.info("Key: " + entry.getKey() + "    Value: " + entry.getValue());
            }

            reader.delete();
            data.delete();
        }

        // Assert
        assertNotNull(matcher);

        LOG.info("Delete all");
        matcher.delete();
        LOG.info("Delete ticket");
        ticket.delete();
        LOG.info("All deleted");
    }

    @Test
    void cti_createBoundingBox() {
        // Act
        Point topLeft = new Point(10, 234);
        Point bottomRight = new Point(30, 400);
        BoundingBox boundingBox = new BoundingBox(topLeft, bottomRight);

        // Assert
        assertNotNull(boundingBox);
        assertEquals(topLeft, boundingBox.getTopLeft());
        assertEquals(bottomRight, boundingBox.getBottomRight());
    }

    @Test
    void cti_createText() {
        // Act
        Point topLeft = new Point(50, 234);
        Point bottomRight = new Point(165, 400);
        BoundingBox boundingBox = new BoundingBox(topLeft, bottomRight);
        Text text = new Text("code", boundingBox);

        // Assert
        assertNotNull(text);
        assertEquals("code", text.getKey());
        assertEquals(boundingBox, text.getBoundingBox());
    }

    @Test
    void cti_createTicketImage() throws URISyntaxException {
        // Act
        String imagePath = Paths.get(CtiTest.class.getResource("templateimages/k.jpg").toURI())
                .toAbsolutePath()
                .toString();
        TicketImage image = new TicketImage(imagePath);

        // Assert
        assertNotNull(image);
        assertEquals(imagePath, image.getImagePath());
    }

    @Test
    void cti_createTicket() throws URISyntaxException {
        // Act
        String imagePath = Paths.get(CtiTest.class.getResource("templateimages/k.jpg").toURI())
                .toAbsolutePath()
                .toString();
        TicketImage image = new TicketImage(imagePath);

        List<Text> texts = new ArrayList<>();
        {
            Point topLeft = new Point(50, 234);
            Point bottomRight = new Point(165, 400);
            BoundingBox boundingBox = new BoundingBox(topLeft, bottomRight);
            Text text = new Text("code", boundingBox);
            texts.add(text);
        }
        {
            Point topLeft = new Point(75, 834);
            Point bottomRight = new Point(90, 900);
            BoundingBox boundingBox = new BoundingBox(topLeft, bottomRight);
            Text text = new Text("test", boundingBox);
            texts.add(text);
        }

        Ticket ticket = new Ticket("ticketname", image, texts);

        // Assert
        assertNotNull(ticket);
        assertEquals("ticketname", ticket.getName());
        assertEquals(image, ticket.getImage());
        assertEquals(texts, ticket.getTexts());
    }

    private TicketImage getRandomTicketImage() throws IOException, URISyntaxException {
        return new TicketImage(getRandomTemplateImagePath().toString());
    }

    private Path getRandomTemplateImagePath() throws IOException, URISyntaxException {
        List<Path> imagePaths = getAllTemplateImagePaths();
        return imagePaths.get(randomInt(0, imagePaths.size() - 1));
    }

    private List<Path> getAllTemplateImagePaths() throws URISyntaxException, IOException {
        Path directory = Paths.get(CtiTest.class.getResource("templateimages").toURI());
        return Files.list(directory)
                .map(Path::toAbsolutePath)
                .collect(Collectors.toList());
    }

    private BoundingBox randomBoundingBox(Point min, Point max) {
        Point topLeft = new Point(randomInt(min.getX(), max.getX()), randomInt(min.getY(), max.getY()));
        Point bottomRight = new Point(randomInt(topLeft.getX(), max.getX()), randomInt(topLeft.getY(), max.getX()));
        return new BoundingBox(topLeft, bottomRight);
    }

    private int randomInt(int min, int max) {
        return (new Random()).nextInt(max + 1 - min) + min;
    }

    private Path extract(InputStream resource) throws IOException {
        String tmpDir = System.getProperty("java.io.tmpdir");
        Path file = Paths.get(tmpDir, "file.jpg");
        Files.copy(resource, file, StandardCopyOption.REPLACE_EXISTING);
        return file;
    }
}
