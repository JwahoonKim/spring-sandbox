package network.tcp.v2;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientV2 {

    private static final int PORT =  12345;

    public static void main(String[] args) throws java.io.IOException {
        // 클라이언트 시작
        Socket socket = new Socket("localhost", PORT);

        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

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

        // 서버로부터 문자 받기

        // 자원 정리
        input.close();
        output.close();
        socket.close();
    }
}
