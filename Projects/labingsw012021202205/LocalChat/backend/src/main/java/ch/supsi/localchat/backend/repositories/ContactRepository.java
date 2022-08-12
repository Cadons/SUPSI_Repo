package ch.supsi.localchat.backend.repositories;

import ch.supsi.localchat.backend.model.Contact;
import ch.supsi.localchat.backend.model.User;
import ch.supsi.localchat.backend.repositories.util.FileTools;
import ch.supsi.localchat.backend.services.IContactRepository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ContactRepository implements IContactRepository {

    /**
     * @apiNote contacts contains all users of the chat system
     */
    private User user;
    private List<Contact> globalUsers;

    public ContactRepository() {


    }

    /**
     * @apiNote Load contacts from json file and put those in a collection
     */
    public boolean load(String username) {
        try {
            if (PreferencesRepositories.checkDirectory() && PreferencesRepositories.checkUserFolders(username)) {
                var contact = new JSONObject(FileTools.ReadFile(PreferencesRepositories.contactFolder() + username + PreferencesRepositories.separator() + "info.json"));//get file//this return an array of objects

                String contactUsername = (String) contact.get("username");
                Contact userContact = new Contact(contactUsername);
                JSONArray contacts = (JSONArray) contact.get("contacts");
                var usr = new User(userContact);

                contacts.forEach(e -> usr.addContact(new Contact((String) e)));

                this.user = usr;
                return true;
            } else {

                return false;

            }
        } catch (Exception e) {

            return false;

        }

    }

    /**
     * @apiNote Add new user to system
     */
    @Override
    public void addUser(String username) throws NoUserLoadedException {
        File contact = new File(PreferencesRepositories.contactFolder() + username);
        if (PreferencesRepositories.checkDirectory()) {
            if (!PreferencesRepositories.checkUserFolders(username)) {
                contact.mkdir();
                getAll();
                var userTmp = new Contact(username);
                globalUsers.add(userTmp);
                user = new User(userTmp);

                UserPreferencesRepository.setDefaultSettings(username);//setup settings
                save();
                unload();

            }
        } else {
            addUser(username);
        }

    }

    @Override
    public boolean isPresent(String username) {
        return PreferencesRepositories.checkUserFolders(username);
    }

    @Override
    public User getUser() throws NoUserLoadedException {
        if (user != null)
            return user;
        else
            throw new NoUserLoadedException();
    }

    @Override
    public List<Contact> getAnonymousChat() {
        var userContacts = user.getContacts();
        var globalContacts = this.getAll();
        ArrayList<Contact> anonymousUsers = new ArrayList<>();
        var userDirectory = new File(PreferencesRepositories.chatFolder()).listFiles();
        List<Contact> contacts = new ArrayList<>();
        for (File f : userDirectory) {
            String name = f.getName().replace(PreferencesRepositories.chatFolder(), "");
            String username = name.substring(0, f.getName().indexOf('-')).replace(".json", "");
            Pattern regex = Pattern.compile("^*-" + user.getUserInfo().getUsername());
            Matcher matcher = regex.matcher(name);
            if (matcher.find()) {
                var user = globalContacts.stream().filter(e -> e.getUsername().equals(username)).findFirst().orElse(null);
                if (user != null)
                    if (!userContacts.contains(user))
                        anonymousUsers.add(user);
            }
        }
        return anonymousUsers;
    }

    @Override
    public Contact getUserContact() throws NoUserLoadedException {

        if (user != null)
            return this.user.getUserInfo();
        else
            throw new NoUserLoadedException();
    }

    /**
     * @apiNote Saves contacts on the json file
     */
    public void save() throws NoUserLoadedException {

        if (user != null) {
            PreferencesRepositories.checkDirectory();
            JSONObject fileContent = new JSONObject();
            JSONObject users = new JSONObject();

            fileContent.put("username", user.getUserInfo().getUsername());
            JSONArray contactsOfUser = new JSONArray();
            user.getContacts().forEach(username -> contactsOfUser.put(username.getUsername()));
            fileContent.put("contacts", contactsOfUser);
            var contactFolder = new File(PreferencesRepositories.contactFolder() + user.getUserInfo().getUsername());
            if (!contactFolder.exists())
                contactFolder.mkdir();

            FileTools.WriteFile(PreferencesRepositories.contactFolder() + user.getUserInfo().getUsername() + File.separator + "info.json", fileContent.toString());
        } else {
            throw new NoUserLoadedException();
        }


    }

    /**
     * @return list of all contacts
     */
    @Override
    public List<Contact> getAll() {
        if (PreferencesRepositories.checkDirectory()) {
            if (globalUsers == null) {
                var userDirectory = new File(PreferencesRepositories.contactFolder()).listFiles(File::isDirectory);
                List<Contact> contacts = new ArrayList<>();
                for (File f : userDirectory) {
                    contacts.add(new Contact(f.getName()));
                }
                globalUsers = contacts;
                return contacts;
            } else {
                return globalUsers;
            }
        } else {
            return new ArrayList<>();//void list, no users
        }
    }

    @Override
    public void unload() {
        user = null;
    }


}
