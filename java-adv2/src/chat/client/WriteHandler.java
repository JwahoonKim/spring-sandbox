package chat.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class WriteHandler implements Runnable {

    private static final String DELIMITER = "|";

    private final DataOutputStream output;
    private final Client client;
    private boolean closed = false;

    public WriteHandler(DataOutputStream output, Client client) {
        this.output = output;
        this.client = client;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        try {
            String username = getUsername(scanner);
            output.writeUTF("/join" + DELIMITER + username);

            while (true) {
                String message = scanner.nextLine(); // 블로킹
                if (message.isEmpty()) continue;

                if (message.equals("/exit")) {
                    output.writeUTF(message);
                    break;
                }

                // "/" 로 시작하면 명령어, 나머지는 일반 메시지
                if (message.startsWith("/")) {
                    output.writeUTF(message);
                } else {
                    output.writeUTF("/message" + DELIMITER + message);
                }


            }

        } catch (IOException | NoSuchElementException e) {
            throw new RuntimeException(e);
        } finally {
            client.close();
        }

    }

    private static String getUsername(Scanner scanner) {
        System.out.println("이름을 입력하세요.");
        String username;
        do {
            username = scanner.nextLine();
        } while (username.isBlank());
        return username;
    }

    public synchronized void close() {
        if (closed) return;

        try {
            System.in.close(); // Scanner 입력 중지 (사용자의 입력을 닫음)
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        closed = true;
    }
}
