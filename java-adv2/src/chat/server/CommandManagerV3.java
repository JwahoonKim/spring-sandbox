package chat.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import chat.server.command.ChangeCommand;
import chat.server.command.Command;
import chat.server.command.DefaultCommand;
import chat.server.command.ExitCommand;
import chat.server.command.JoinCommand;
import chat.server.command.MessageCommand;
import chat.server.command.UsersCommand;

public class CommandManagerV3 implements CommandManager {

    private static final String DELIMETER = "\\|";
    private final Map<String, Command> commands = new HashMap<>();
    private final Command defaultCommand = new DefaultCommand();

    public CommandManagerV3(SessionManager sessionManager) {
        commands.put("/join", new JoinCommand(sessionManager));
        commands.put("/message", new MessageCommand(sessionManager));
        commands.put("/change", new ChangeCommand(sessionManager));
        commands.put("/users", new UsersCommand(sessionManager));
        commands.put("exit", new ExitCommand(sessionManager));
    }

    @Override
    public void execute(String totalMessage, Session session) throws IOException {
        String[] split = totalMessage.split(DELIMETER);
        String command = split[0];
        Command cmd = commands.getOrDefault(command, defaultCommand);
        cmd.execute(split, session);
    }
}
