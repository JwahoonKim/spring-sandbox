package was.v6;

import java.io.IOException;
import java.util.List;

import was.httpserver.HttpServer;
import was.httpserver.ServletManager;
import was.httpserver.servlet.DiscardServlet;
import was.httpserver.servlet.reflection.ReflectionServlet;
import was.v5.servlet.HomeServlet;

public class ServerMainV6 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        List<Object> controllers = List.of(new SiteControllerV6(), new SearchControllerV6());
        ReflectionServlet reflectionServlet = new ReflectionServlet(controllers);

        ServletManager servletManager = new ServletManager();
        servletManager.setDefaultServlet(reflectionServlet); // reflectionServlet을 default로 설정하는것이 중요하다.
        servletManager.add("/", new HomeServlet()); // 이름 없는 메서드를 만들 수가 없어서..
        servletManager.add("/favicon.ico", new DiscardServlet()); // favicon.ico 같은 메서드를 만들 수 없어서..

        HttpServer server = new HttpServer(PORT, servletManager);
        server.start();
    }

}
