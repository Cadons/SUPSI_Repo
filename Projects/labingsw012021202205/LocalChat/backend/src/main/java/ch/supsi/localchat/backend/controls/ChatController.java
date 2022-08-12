package ch.supsi.localchat.backend.controls;


import ch.supsi.localchat.backend.model.Message;
import ch.supsi.localchat.backend.services.ChatServices;
import ch.supsi.localchat.backend.services.UserServices;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.Map;

public class ChatController {
    UserServices userServ = UserServices.instance();
    ChatServices chatServ = ChatServices.instance();

    public String getChat(String receiver) {

        var target = userServ.getContact(receiver);
        if (target != null) {
            var chat = chatServ.getChat(target);
            if (chat != null) {
                return jsonChat(chat.getMessages());
            }
        }
        return new JSONArray().toString();
    }

    private String jsonChat(Map<LocalDateTime, Message> tmpList) {
        JSONArray result = new JSONArray();
        tmpList.forEach((k, e) -> {
            JSONObject contact = new JSONObject();
            contact.put("from", e.getFrom().getUsername());
            contact.put("to", e.getTo().getUsername());
            contact.put("time", chatServ.getMessageHHMM(e));
            contact.put("text", e.getText());
            result.put(contact);
        });
        return result.toString();
    }

    public boolean sendMessage(String msg) {
        if (chatServ.addMessage(msg))
            return true;
        return false;
    }


}
