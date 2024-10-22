package chat.server.command;

import java.io.IOException;

import chat.server.Session;
import chat.server.SessionManager;

public class ExitCommand implements Command {

    private final SessionManager sessionManager;

    public ExitCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String[] args, Session session) throws IOException {
        throw new IOException("exit");
    }
}
