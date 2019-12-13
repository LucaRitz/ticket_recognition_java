import com.bfh.ticket.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {

    static {
        try {
            System.loadLibrary("ticket_recognition_jcpp");
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        // Act
        Matcher matcher = new Matcher(Algorithm.SIFT);

        Path file = extract(Main.class.getResourceAsStream("/Ticket.jpg"));
        List<Text> texts = new ArrayList<>();
        texts.add(new Text("name", new BoundingBox(new Point(0,0), new Point(100,100))));
        Ticket ticket = new Ticket("template_1", new TicketImage(file.toAbsolutePath().normalize().toString()), texts);
        matcher.train(ticket);

        Optional<TicketMatch> match = matcher.match(new TicketImage(file.toAbsolutePath().normalize().toString()));
        System.out.println("Is present: " + match.isPresent());
        if (match.isPresent()) {
            System.out.println("Matched name: " + match.get().getTicket().getName());
            System.out.println("ticket image: " + match.get().getTicket().getImage().getImagePath());

            MetadataReader reader = new MetadataReader(Algorithm.SIFT);
            Metadata data = reader.read(ticket, new TicketImage(file.toAbsolutePath().normalize().toString()));
            Map<String, String> extracted = data.getTexts();
            for(Map.Entry<String, String> entry : extracted.entrySet()) {
                System.out.println("Key: " + entry.getKey() + "    Value: " + entry.getValue());
            }

            reader.delete();
            data.delete();
        }

        // Assert

        System.out.println("Delete all");
        matcher.delete();
        System.out.println("Delete ticket");
        ticket.delete();
        System.out.println("All deleted");
    }

    private static Path extract(InputStream resource) throws IOException {
        String tmpDir = System.getProperty("java.io.tmpdir");
        Path file = Paths.get(tmpDir, "file.jpg");
        Files.copy(resource, file, StandardCopyOption.REPLACE_EXISTING);
        return file;
    }
}
