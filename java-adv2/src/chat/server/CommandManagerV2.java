package chat.server;

import java.io.IOException;
import java.util.List;

public class CommandManagerV2 implements CommandManager {

    private static final String DELIMETER = "\\|";
    private final SessionManager sessionManager;

    public CommandManagerV2(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String totalMessage, Session session) throws IOException {
        if (totalMessage.startsWith("/join")) {
            String[] split = totalMessage.split(DELIMETER);
            String username = split[1];
            session.setUsername(username);
            sessionManager.sendAll(username + "님이 입장하셨습니다.");
        } else if (totalMessage.startsWith("/message")) {
            String[] split = totalMessage.split(DELIMETER);
            String message = split[1];
            sessionManager.sendAll(session.getUsername() + ": " + message);
        } else if (totalMessage.startsWith("/change")) {
            String[] split = totalMessage.split(DELIMETER);
            String username = split[1];
            sessionManager.sendAll(session.getUsername() + "님이 " + username + "으로 변경하셨습니다.");
            session.setUsername(username);
        } else if (totalMessage.startsWith("/users")) {
            List<String> usernames = sessionManager.getAllUsernames();
            StringBuilder sb = new StringBuilder();
            sb.append("현재 접속자: ");
            for (String username : usernames) {
                sb.append(username).append(", ");
            }
            session.send(sb.toString());
        } else if (totalMessage.startsWith("/exit")) {
            throw new IOException("exit");
        } else {
            session.send("알 수 없는 명령어입니다.");
        }
    }
}
