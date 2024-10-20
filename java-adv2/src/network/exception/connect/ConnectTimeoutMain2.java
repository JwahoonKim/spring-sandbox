package network.exception.connect;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectTimeoutMain2 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        try {
            Socket socket = new Socket(); // 상대방이 응답을 안하는 상황
            socket.connect(new InetSocketAddress("192.168.1.250", 45678), 1000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long end = System.currentTimeMillis();
        System.out.println("소요시간 : " + (end - start) + "ms");
    }
}
