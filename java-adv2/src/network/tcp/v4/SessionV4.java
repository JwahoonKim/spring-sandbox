package network.tcp.v4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import network.tcp.SocketCloseUtil;

public class SessionV4 implements Runnable{

    private final Socket socket;

    public SessionV4(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        DataInputStream input = null;
        DataOutputStream output = null;
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

            while (true) {
                // 클라이언트로부터 문자 받기
                String received = input.readUTF();

                // 클라이언트에게 문자 보내기
                output.writeUTF(received  + " World!");

                if (received.equals("exit")) {
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
