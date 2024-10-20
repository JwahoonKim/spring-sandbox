package network.tcp.v5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerV5 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        while (true) {
            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket socket = serverSocket.accept(); // 블로킹

            SessionV5 session = new SessionV5(socket);
            Thread thread = new Thread(session);
            thread.start();

            serverSocket.close();
        }
    }
}
