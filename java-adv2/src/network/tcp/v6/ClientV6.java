package network.tcp.v6;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientV6 {

    private static final int PORT =  12345;

    public static void main(String[] args) throws IOException {
        // 클라이언트 시작
        try(
            Socket socket = new Socket("localhost", PORT);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        ) {
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
        }
    }
}
