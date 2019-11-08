package com.bfh.ticket;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CtiTest {

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
    void cti_createMatcherAndDelete_matcherCreatedd() {
        // Act
        Matcher matcher = cti.matcher(Algorithms.SIFT.name());

        // Assert
        assertNotNull(matcher);

        matcher.delete();
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
}
