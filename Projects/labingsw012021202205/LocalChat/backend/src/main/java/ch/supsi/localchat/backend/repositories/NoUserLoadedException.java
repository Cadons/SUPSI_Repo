package ch.supsi.localchat.backend.repositories;

public class NoUserLoadedException extends Exception {
    @Override
    public String getMessage() {
        return "No user loaded";
    }

}
