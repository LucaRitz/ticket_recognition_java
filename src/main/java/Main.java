import com.bfh.ticket.Cti;

public class Main {

    static {
        try {
            System.loadLibrary("ticket_recognition_jcpp");
        } catch(Exception exc) {
            exc.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Cti cti = new Cti();
        System.out.println(cti.helloWorld());
    }
}
