package ch.supsi.localchat.backend.controls;

import ch.supsi.localchat.backend.model.Chat;
import ch.supsi.localchat.backend.model.Contact;
import ch.supsi.localchat.backend.model.Message;

public interface IChatServices {
    public boolean addMessage(String msg);

    public Chat getChat(Contact receiver);

    public String getMessageHHMM(Message msg);
}
