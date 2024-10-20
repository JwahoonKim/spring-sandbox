package network.tcp.v2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerV2 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        // 서버시작
        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket socket = serverSocket.accept();

        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        while (true) {
            // 클라이언트로부터 문자 받기
            String received = input.readUTF();

            // 클라이언트에게 문자 보내기
            output.writeUTF(received  + " World!");

            if (received.equals("exit")) {
                break;
            }
        }

        // 자원 정리
        input.close();
        output.close();
        socket.close();
        serverSocket.close();
    }
}
