package was.httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import was.v2.HttpRequestHandlerV2;

public class HttpServer {

    private final int port;
    private final ExecutorService es = Executors.newFixedThreadPool(10);
    private final ServletManager servletManager;

    public HttpServer(int port, ServletManager servletManager) {
        this.port = port;
        this.servletManager = servletManager;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            Socket socket = serverSocket.accept();
            HttpRequestHandler handler = new HttpRequestHandler(socket, servletManager);
            es.submit(handler);
        }
    }
}
