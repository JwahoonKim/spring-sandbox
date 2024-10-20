package network.tcp.v1;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressMain {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress localhosts = InetAddress.getByName("localhost");
        System.out.println(localhosts); // localhost/127.0.0.1

        InetAddress google = InetAddress.getByName("google.com");
        System.out.println("google = " + google); // google = google.com/142.250.198.14
    }
}
