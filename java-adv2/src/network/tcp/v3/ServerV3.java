package network.tcp.v3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerV3 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        while (true) {
            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket socket = serverSocket.accept(); // 블로킹

            SessionV3 session = new SessionV3(socket);
            Thread thread = new Thread(session);
            thread.start();

            serverSocket.close();
        }
    }
}
