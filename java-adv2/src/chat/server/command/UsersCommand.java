package chat.server.command;

import java.io.IOException;
import java.util.List;

import chat.server.Session;
import chat.server.SessionManager;

public class UsersCommand implements Command {

    private final SessionManager sessionManager;

    public UsersCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String[] args, Session session) throws IOException {
        List<String> usernames = sessionManager.getAllUsernames();
        StringBuilder sb = new StringBuilder();
        sb.append("현재 접속자: ");
        for (String username : usernames) {
            sb.append(username).append(", ");
        }
        session.send(sb.toString());
    }
}
