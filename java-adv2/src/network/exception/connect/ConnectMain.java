package network.exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectMain {

    public static void main(String[] args) throws IOException {
        unknownHostException();
        connectionRefused();
    }

    private static void unknownHostException() throws IOException {
        try {
            // 이상한 host 연결
            Socket socket = new Socket("999.999.999.999", 12345);
        } catch (UnknownHostException e) { // host를 알 수 없다.
            throw new RuntimeException(e);
        }
    }

    private static void connectionRefused() throws IOException {
        try {
            // 서버가 없는 host 연결
            Socket socket = new Socket("localhost", 12345);
        } catch (ConnectException e) { // 연결이 거절되었다는 것
            throw new RuntimeException(e);
        }
    }

}
