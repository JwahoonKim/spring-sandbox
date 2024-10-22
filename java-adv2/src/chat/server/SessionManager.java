package chat.server;

import java.util.ArrayList;
import java.util.List;

public class SessionManager {

    private final List<Session> sessions = new ArrayList<>();

    public synchronized void add(Session session) {
        sessions.add(session);
    }

    public synchronized void remove(Session session) {
        sessions.remove(session);
    }

    public synchronized void closeAll() {
        for (Session session : sessions) {
            session.close();
        }
        sessions.clear();
    }

    public void sendAll(String message) {
        for (Session session : sessions) {
            try {
                session.send(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public synchronized List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();
        for (Session session : sessions) {
            usernames.add(session.getUsername());
        }
        return usernames;
    }
}
