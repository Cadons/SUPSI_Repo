package ch.supsi.localchat.backend.controls;

import ch.supsi.localchat.backend.model.Contact;
import ch.supsi.localchat.backend.services.UserServices;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class UserController {
    UserServices userServ = UserServices.instance();
    private boolean checkLogin;

    //CHAT'S PART OF USER

    public boolean isLogged() {
        return checkLogin;
    }

    public boolean addContact(String usernameToAdd) {
        var contact = userServ.searchContacts(usernameToAdd).stream().filter(e -> e.getUsername().equals(usernameToAdd)).findFirst().orElse(null);
        return userServ.addContact(contact);
    }

    public String searchContact(String username) {
        var tmpList = userServ.searchContacts(username);
        return jsonContacts(tmpList);
    }

    private String jsonContacts(List<Contact> tmpList) {
        JSONArray result = new JSONArray();
        tmpList.forEach((e) -> {
            JSONObject contact = new JSONObject();
            contact.put("username", e.getUsername());
            result.put(contact);
        });
        return result.toString();
    }

    public boolean login(String username) {
        checkLogin = userServ.login(username);
        return checkLogin;
    }

    public void logout() {
        userServ.logout();
    }

    public boolean registerUser(String username) {

        if (!userServ.newUser(username)) {
            return false;
        }
        return true;

    }

    public String getActiveUser() {
        return UserServices.getActiveUser().getUserInfo().getUsername();
    }

    public String getContacts() {
        return jsonContacts(userServ.getContactList());
    }

    public String getAnonymous() {
        return jsonContacts(userServ.getAnonymousList());
    }


}
