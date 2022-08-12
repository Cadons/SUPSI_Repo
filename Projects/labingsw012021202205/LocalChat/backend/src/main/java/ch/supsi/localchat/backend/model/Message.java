package ch.supsi.localchat.backend.model;

import java.time.LocalDateTime;

public class Message {
    private final Contact from;
    private final Contact to;
    private final LocalDateTime time;
    private final String text;

    /**
     * @param from who has sent message
     * @param to   who receive message
     * @param time time of message sending
     * @param text content of the message
     */
    public Message(Contact from, Contact to, LocalDateTime time, String text) {
        this.from = from;
        this.to = to;
        this.time = time;
        this.text = text;
    }

    public Contact getTo() {
        return to;
    }

    public Contact getFrom() {
        return from;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getText() {
        return text;
    }
}
