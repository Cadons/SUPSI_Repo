package ch.supsi.localchat.backend.services;

import ch.supsi.localchat.backend.model.Chat;
import ch.supsi.localchat.backend.model.Contact;
import ch.supsi.localchat.backend.repositories.NoChatLoadedException;

public interface IChatRepository {

    boolean isPresent(Contact A, Contact B);

    public void save() throws NoChatLoadedException;

    public boolean load(Contact A, Contact B);

    public Chat getChat();

    void addChat(Chat myChat) throws NoChatLoadedException;

    void unload();

}
