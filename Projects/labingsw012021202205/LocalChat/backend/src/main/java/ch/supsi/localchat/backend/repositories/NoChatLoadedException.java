package ch.supsi.localchat.backend.repositories;

public class NoChatLoadedException extends Exception {
    @Override
    public String getMessage() {
        return "No chat loaded";
    }

}
