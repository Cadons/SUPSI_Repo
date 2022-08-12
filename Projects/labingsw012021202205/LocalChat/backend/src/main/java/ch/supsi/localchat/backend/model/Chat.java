package ch.supsi.localchat.backend.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

public class Chat {
    final private Contact userA, userB;
    final private Map<LocalDateTime, Message> messages = new TreeMap<>();//use treeMap in order to have messages sorted for time


    public Chat(final Contact A, final Contact B) {
        this.userA = A;
        this.userB = B;
    }

    public Map<LocalDateTime, Message> getMessages() {
        return messages;
    }

    public void addMessage(LocalDateTime key, Message newMessage) {
        messages.put(key, newMessage);
    }

    public Contact getUserA() {
        return userA;
    }

    public Contact getUserB() {
        return userB;
    }
}
