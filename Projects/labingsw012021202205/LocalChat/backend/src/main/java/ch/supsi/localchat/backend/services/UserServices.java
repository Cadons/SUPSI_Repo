package ch.supsi.localchat.backend.services;

import ch.supsi.localchat.backend.controls.IUserServices;
import ch.supsi.localchat.backend.model.Contact;
import ch.supsi.localchat.backend.model.User;
import ch.supsi.localchat.backend.repositories.ChatRepository;
import ch.supsi.localchat.backend.repositories.ContactRepository;
import ch.supsi.localchat.backend.repositories.NoUserLoadedException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UserServices implements IUserServices {

    private static ContactRepository contactRepo = new ContactRepository();
    private static User activeUser;
    private static UserServices userServices;
    private final ChatRepository chatRepo = new ChatRepository();


    protected UserServices() {
        //default contructor
    }

    //constructor for singletone instances
    public static UserServices instance() {
        if (userServices == null)
            userServices = new UserServices();
        return userServices;
    }

    /**
     * @return active user
     */
    public static User getActiveUser() {
        return activeUser;
    }

    /**
     * @param username the username to try for login
     * @return true if the user exist, false if it does not
     * @apiNote Check if user with given username is present (checks filesystem)
     */
    public boolean doesUserExist(String username) {
        return contactRepo.isPresent(username);
    }

    /**
     * @return the list of contact of the Active User
     * @apiNote check if user exist and return its contact list
     */
    public List<Contact> getContactList() {
        if (activeUser != null)
            return activeUser.getContacts();
        return null;
    }

    /**
     * @param username
     * @return true if exists, false if not
     * @apiNote given an username, search for user in repo, if it exist, load it as Active User
     * and return true, otherwise return false
     */
    public boolean login(String username) {
        if (contactRepo.isPresent(username)) {
            contactRepo.load(username);
            try {
                activeUser = contactRepo.getUser();
            } catch (NoUserLoadedException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * @apiNote unload user from activeUser both in repo and services and make savings
     */
    public void logout() {
        try {
            contactRepo.save();
        } catch (NoUserLoadedException e) {
            e.printStackTrace();
        }
        contactRepo.unload();
        activeUser = null;
    }

    /**
     * @param contact Contact to be added to User usr
     * @return true if succeded in adding contact, false if not.
     * @apiNote check if there is an active user, check if the contact exist, check if the active user
     * doesn't already have that contact in his list.
     */
    public boolean addContact(Contact contact) {
        if (activeUser != null)
            if (contactRepo.isPresent(contact.getUsername()))
                if (!activeUser.getContacts().contains(contact)) {
                    activeUser.addContact(contact);
                    try {
                        contactRepo.save();
                    } catch (NoUserLoadedException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
        return false;
    }

    /**
     * @param username
     * @return true if added new user, false if already exist
     */
    public boolean newUser(String username) {
        if (contactRepo.isPresent(username))
            return false; //already exist
        try {
            contactRepo.addUser(username);
        } catch (NoUserLoadedException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * @param str the substring to look for into contact usernames
     * @return the list of contact containing str in their usernames
     * @apiNote search among all existing contacts that has the str corrispondence
     */
    public List<Contact> searchContacts(String str) {
        return contactRepo.getAll().stream()
                .filter(c -> c.getUsername().contains(str))
                .sorted(Comparator.comparing(Contact::getUsername))
                .collect(Collectors.toList());
    }

    /**
     * @param username
     * @return contact
     * @apiNote return a contact (that the active user has in list) given his username
     */
    public Contact getContact(String username) {
        //return searchContacts(username).get(0);
        return activeUser.getContacts().stream()
                .filter(e -> e.getUsername().equals(username))
                .limit(1)
                .findAny().orElse(null);
    }

    public List<Contact> getAnonymousList() {
        return contactRepo.getAnonymousChat();
    }
}
