package network.chat.practice;

import java.util.ArrayList;
import java.util.List;

public class ChatSessionManager {
    private List<ChatSession> sessions = new ArrayList<>();

    public synchronized void add(ChatSession session) {
        sessions.add(session);
    }

    public synchronized void remove(ChatSession session) {
        sessions.remove(session);
    }

    public List<ChatSession> getSessions() {
        return sessions;
    }
}
