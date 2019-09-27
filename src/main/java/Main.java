import com.bfh.ticket.Algorithms;
import com.bfh.ticket.Cti;
import com.bfh.ticket.Matcher;
import com.bfh.ticket.MetadataReader;

public class Main {

    static {
        try {
            System.loadLibrary("ticket_recognition_jcpp");
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Cti cti = new Cti();
        Matcher matcher = cti.matcher(Algorithms.SIFT.name());
        System.out.println(matcher);
        MetadataReader reader = cti.reader(Algorithms.SIFT.name());

        /*TicketImage image = new TicketImage("a_picture.jpg");
        Optional<TicketMatch> matched = matcher.match(image);
        matched.ifPresent(ticketMatch -> System.out.println(ticketMatch.getName()));
        Metadata data = reader.read(new Ticket("", image, Collections.emptyList(), Collections.emptyList()), image);
        System.out.println(data.getTexts().get("TourCode"));*/
    }
}
