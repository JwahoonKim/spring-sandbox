package chat.server.command;

import java.io.IOException;

import chat.server.Session;

public class DefaultCommand implements Command {

    @Override
    public void execute(String[] args, Session session) throws IOException {
        session.send("알 수 없는 명령어입니다.");
    }
}
