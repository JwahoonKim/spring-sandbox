package chat.client;

import java.io.DataInputStream;
import java.io.IOException;

public class ReadHandler implements Runnable {

    private final DataInputStream input;
    private final Client client;
    public boolean closed = false;

    public ReadHandler(DataInputStream input, Client client) {
        this.input = input;
        this.client = client;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = input.readUTF();
                System.out.println(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                client.close();
            }
        }
    }

    public synchronized void close() {
        if (closed) return;
        closed = true;
    }
}
