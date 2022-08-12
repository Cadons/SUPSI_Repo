package ch.supsi.localchat.backend.services;

import ch.supsi.localchat.backend.controls.IChatServices;
import ch.supsi.localchat.backend.model.Chat;
import ch.supsi.localchat.backend.model.Contact;
import ch.supsi.localchat.backend.model.Message;
import ch.supsi.localchat.backend.repositories.ChatRepository;
import ch.supsi.localchat.backend.repositories.NoChatLoadedException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatServices implements IChatServices {
    private static Chat activeChat;
    private static ChatServices chatServices;
    private ChatRepository chatRepo = new ChatRepository();

    protected ChatServices() {
        //default constructor
    }

    //constructor for singletone instances
    public static ChatServices instance() {
        if (chatServices == null)
            chatServices = new ChatServices();
        return chatServices;
    }

    /**
     * @param msg
     * @return true if added correctly, false if not
     * @apiNote add a message to the current active chat
     */
    public boolean addMessage(String msg) {
        LocalDateTime tmpTime = LocalDateTime.now(); //get time

        if (activeChat == null)
            return false; //no chat active

        Contact sender, receiver;
        sender = UserServices.getActiveUser().getUserInfo();

        if (sender == activeChat.getUserA())
            receiver = activeChat.getUserB();
        else
            receiver = activeChat.getUserA();

        chatRepo.getChat().addMessage(tmpTime, new Message(sender, receiver, tmpTime, msg));

        try {
            chatRepo.save();
        } catch (NoChatLoadedException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * @param receiver the receiver contact
     * @return the chat, new if just created or the existent one
     * @apiNote get chat between active user and a contact (the receiver)
     */
    public Chat getChat(Contact receiver) {
        if (activeChat != null)
            closeActiveChat();

        if (!chatRepo.isPresent(UserServices.getActiveUser().getUserInfo(), receiver)) {
            try {
                chatRepo.addChat(new Chat(UserServices.getActiveUser().getUserInfo(), receiver));
            } catch (NoChatLoadedException e) {
                e.printStackTrace();
            }
        } else
            chatRepo.load(UserServices.getActiveUser().getUserInfo(), receiver);

        activeChat = chatRepo.getChat();
        return activeChat;
    }

    /**
     * @apiNote close the precedent active chat and make savings, unloading
     * the active chat from both repository and services classes
     */
    private void closeActiveChat() {
        //check if chat on repo or services has been unloaded
        if (activeChat == null || chatRepo.getChat() == null)
            return;

        try {
            chatRepo.save();
        } catch (NoChatLoadedException e) {
            e.printStackTrace();
        }

        chatRepo.unload();
        activeChat = null;
    }

    /**
     * @param msg message which to get time in HH:MM format
     * @return a string containing HH:MM
     */
    public String getMessageHHMM(Message msg) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return formatter.format(msg.getTime());
    }
}
