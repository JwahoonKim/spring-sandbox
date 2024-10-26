package was.v3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpRequestHandlerV3 implements Runnable {

    private final Socket socket;

    public HttpRequestHandlerV3(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            process();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void process() throws IOException {
        try (
            socket;
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), false, StandardCharsets.UTF_8)
        ) {
            String requestString = requestToString(reader);

            if (requestString.contains("/favicon.ico")) {
                return;
            }

            if (requestString.startsWith("GET /site1")) {
                site1(writer);
            } else if (requestString.startsWith("GET /site2")) {
                site2(writer);
            } else if (requestString.startsWith("GET /search")) {
                search(writer, requestString);
            } else if (requestString.startsWith("GET / ")) {
                home(writer);
            } else {
                notFound(writer);
            }
        }
    }

    private void home(PrintWriter writer) {
        writer.write("HTTP/1.1 200 OK\r\n");
        writer.write("Content-Type: text/html;\r\n");
        writer.write("\r\n");
        writer.write("<h1>Home</h1>");
        writer.write("<ul>");
        writer.write("<li><a href=\"/site1\">site1</a></li>");
        writer.write("<li><a href=\"/site2\">site2</a></li>");
        writer.write("<li><a href=\"/search?q=hello\">search</a></li>");
        writer.write("</ul>");
        writer.flush();
    }

    private void site1(PrintWriter writer) {
        writer.write("HTTP/1.1 200 OK\r\n");
        writer.write("Content-Type: text/html;\r\n");
        writer.write("\r\n");
        writer.write("<h1>Site1</h1>");
        writer.flush();
    }

    private void site2(PrintWriter writer) {
        writer.write("HTTP/1.1 200 OK\r\n");
        writer.write("Content-Type: text/html;\r\n");
        writer.write("\r\n");
        writer.write("<h1>Site2</h1>");
        writer.flush();
    }

    // /GET /search?q=hello HTTP/1.1
    private void search(PrintWriter writer, String requestString) {
        int startIndex = requestString.indexOf("q=");
        int endIndex = requestString.indexOf(" ", startIndex + 2);
        String query = requestString.substring(startIndex + 2, endIndex);

        writer.write("HTTP/1.1 200 OK\r\n");
        writer.write("Content-Type: text/html;\r\n");
        writer.write("\r\n");
        writer.write("<h1>Search</h1>");
        writer.write("<p>query: " + query + "</p>");
        writer.flush();
    }

    private void notFound(PrintWriter writer) {
        writer.write("HTTP/1.1 404 Not Found\r\n");
        writer.write("Content-Type: text/html;\r\n");
        writer.write("\r\n");
        writer.write("<h1>Not Found</h1>");
        writer.flush();
    }

    private static String requestToString(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
}
