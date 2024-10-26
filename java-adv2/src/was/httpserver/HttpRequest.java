package was.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private String method;
    private String path;
    private final Map<String, String> queryParams = new HashMap<>();
    private final Map<String, String> headers = new HashMap<>();

    public HttpRequest(BufferedReader reader) throws IOException {
        parseRequestLine(reader);
        parseHeaders(reader);
    }

    private void parseRequestLine(BufferedReader reader) throws IOException {
        String requestLine = reader.readLine();
        if (requestLine == null) {
            throw new IOException("Request line is null");
        }

        String[] parts = requestLine.split(" ");
        if (parts.length != 3) {
            throw new IOException("Invalid request line: " + requestLine);
        }

        this.method = parts[0];

        String[] pathParts = parts[1].split("\\?");
        this.path = pathParts[0];
        if (pathParts.length > 1) {
            parseQueryParameters(pathParts[1]);
        }

    }

    private void parseQueryParameters(String queryString) {
        String[] split = queryString.split("&");
        for (String param : split) {
            String[] keyValue = param.split("=");
            queryParams.put(keyValue[0], keyValue[1]);
        }
    }

    private void parseHeaders(BufferedReader reader) {
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    break;
                }
                String[] parts = line.split(": ");
                headers.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getParameter(String name) {
        return queryParams.get(name);
    }

    public String getHeader(String name) {
        return headers.get(name);
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
            "method='" + method + '\'' +
            ", path='" + path + '\'' +
            ", queryParams=" + queryParams +
            ", headers=" + headers +
            '}';
    }
}
