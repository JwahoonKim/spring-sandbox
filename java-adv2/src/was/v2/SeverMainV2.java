package was.v2;

import java.io.IOException;

import was.v1.HttpServerV1;

public class SeverMainV2 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        HttpServerV2 server = new HttpServerV2(PORT);
        server.start();
    }

}
