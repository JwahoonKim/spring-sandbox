package was.httpserver.servlet;

import java.io.IOException;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;

public interface HttpServlet {
    void service(HttpRequest request, HttpResponse response) throws IOException;
}
