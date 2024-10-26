package was.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpRequestHandler implements Runnable {

    private final Socket socket;
    private final ServletManager servletManager;

    public HttpRequestHandler(Socket socket, ServletManager servletManager) {
        this.socket = socket;
        this.servletManager = servletManager;
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

            HttpRequest request = new HttpRequest(reader);
            HttpResponse response = new HttpResponse(writer);

            servletManager.execute(request, response);
            response.flush();
        }
    }

    private void home(HttpResponse response) {
       response.writeBody("<h1>Home</h1>");
        response.writeBody("<ul>");
        response.writeBody("<li><a href=\"/site1\">site1</a></li>");
        response.writeBody("<li><a href=\"/site2\">site2</a></li>");
        response.writeBody("<li><a href=\"/search?q=hello\">search</a></li>");
        response.writeBody("</ul>");
    }

    private void site1(HttpResponse response) {
        response.writeBody("<h1>Site1</h1>");
    }

    private void site2(HttpResponse response) {
        response.writeBody("<h1>Site2</h1>");
    }

    private void search(HttpRequest request, HttpResponse response) {
        String query = request.getParameter("q");
        response.writeBody("<h1>Search</h1>");
        response.writeBody("<p>query: " + query + "</p>");
    }

    private void notFound(HttpResponse response) {
        response.setStatusCode(404);
        response.writeBody("<h1>Not Found</h1>");
    }

}
