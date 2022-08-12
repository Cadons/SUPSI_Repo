package ch.supsi.localchat.backend.repositories;


import ch.supsi.localchat.backend.model.Chat;
import ch.supsi.localchat.backend.model.Contact;
import ch.supsi.localchat.backend.model.Message;
import ch.supsi.localchat.backend.repositories.util.FileTools;
import ch.supsi.localchat.backend.services.IChatRepository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;


public class ChatRepository implements IChatRepository {

    private Chat loadedChat;//chat loaded from json

    /**
     * @return Return the active chat
     */
    public Chat getChat() {
        return loadedChat;
    }

    /**
     * @apiNote load chat between userA and userB from json file
     */
    public boolean load(Contact A, Contact B) {
        try {
            if (PreferencesRepositories.checkDirectory()) {

                String file = A.getUsername() + "-" + B.getUsername() + ".json";
                File path = new File(PreferencesRepositories.chatFolder() + file);
                if (!path.exists()) {
                    file = B.getUsername() + "-" + A.getUsername() + ".json";

                    path = new File(PreferencesRepositories.chatFolder() + file);
                    if (!path.exists()) throw new FileNotFoundException();

                }
                var json = new JSONObject(FileTools.ReadFile(path.getPath()));//get file//this return an array of objects

                final Chat chat = new Chat(A, B);


                JSONArray messages = (JSONArray) json.get("messages");//get contact array
                messages.forEach(k -> {
                    var message = ((JSONObject) k);

                    Contact from;
                    Contact to;
                    if (A.getUsername().equals(message.getString("from"))) {
                        from = A;
                        to = B;
                    } else {
                        from = B;
                        to = A;
                    }
                    chat.addMessage(LocalDateTime.parse((String) message.get("time")), new Message(from, to, LocalDateTime.parse((String) message.get("time")), (String) message.get("text")));

                });

                loadedChat = chat;
                return true;
            } else {

                return false;

            }
        } catch (Exception e) {


            return false;
        }
    }

    /**
     * @apiNote add a new chat, create file and load as active chat, if it already exists it will be loaded from disk
     */
    public void addChat(Chat myChat) throws NoChatLoadedException {

        if (!isPresent(myChat.getUserA(), myChat.getUserB())) {
            loadedChat = myChat;
            save();
        } else {

        }

    }

    /**
     * @apiNote check if a chat between user exists
     */
    @Override
    public boolean isPresent(Contact A, Contact B) {
        if (A != null && B != null) {
            return (new File(PreferencesRepositories.chatFolder() + A.getUsername() + "-" + B.getUsername() + ".json")).exists() || (new File(PreferencesRepositories.chatFolder() + B.getUsername() + "-" + A.getUsername() + ".json")).exists();
        } else {
            return false;
        }
    }

    /**
     * @apiNote Save loadedChat on json file, if it doesn't exist create new file
     */
    public void save() throws NoChatLoadedException {
        if (loadedChat != null) {
            //  var path=new File();
            PreferencesRepositories.checkDirectory();
            String file = PreferencesRepositories.chatFolder() + loadedChat.getUserA().getUsername() + "-" + loadedChat.getUserB().getUsername() + ".json";
            if (!new File(file).exists()) {
                String file2 = PreferencesRepositories.chatFolder() + loadedChat.getUserB().getUsername() + "-" + loadedChat.getUserA().getUsername() + ".json";

                if (new File(file2).exists()) {
                    file = file2;
                }
            }
            JSONObject chat = new JSONObject();
            chat.put("userA", this.loadedChat.getUserA().getUsername());
            chat.put("userB", this.loadedChat.getUserB().getUsername());
            JSONArray myMessages = new JSONArray();
            this.loadedChat.getMessages().forEach((k, v) -> {
                var message = new JSONObject();
                message.put("time", v.getTime());
                message.put("from", v.getFrom().getUsername());
                message.put("to", v.getTo().getUsername());
                message.put("text", v.getText());
                myMessages.put(message);
            });
            chat.put("messages", myMessages);
            FileTools.WriteFile(file, chat.toString());
        } else {
            throw new NoChatLoadedException();
        }

    }

    @Override
    public void unload() {
        loadedChat = null;
    }


}
