package network.tcp.v4;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import network.tcp.SocketCloseUtil;

public class ClientV4 {

    private static final int PORT =  12345;

    public static void main(String[] args) throws java.io.IOException {
        // 클라이언트 시작
        Socket socket = null;
        DataInputStream input = null;
        DataOutputStream output = null;
        try {
            socket = new Socket("localhost", PORT);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

            // 서버에 문자 보내기
            output.writeUTF("Hello");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("전송 문자 : ");
                String message = scanner.nextLine();
                output.writeUTF(message);

                if (message.equals("exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            SocketCloseUtil.closeAll(socket, input, output);
        }
    }
}
